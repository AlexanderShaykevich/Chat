class ChatService {
    private var uniqueChatId = 1
    private var uniqueMessageId = 1
    var chatList = mutableListOf<Chat>()

    private fun getChatId():Int = uniqueChatId++
    private fun getMessageId():Int = uniqueMessageId++

    fun clear () {
        chatList.clear()
    }

    fun deleteChat(chatId: Int): Boolean {
        return (chatList.removeIf { it.chatId == chatId })
    }


    fun getChats(userId: Int): List<Chat> =
        chatList.filter { it.userId == userId }.ifEmpty { throw ChatNotFoundException() }.toList()


    fun getMessagesFromChat(chatId: Int, messageId: Int, amount: Int): List<Message> {
        val chat = chatList.firstOrNull { it.chatId == chatId } ?: return emptyList()

        val outputMessages = chat.messages
            .asSequence()
            .filter { it.messageId >= messageId }
            .take(amount)
            .toList()

        chat.messages.filter { it.messageId >= messageId }.take(amount).forEach { it.read = true }

        return outputMessages
    }


    fun createMessage(userId: Int, receiverId: Int, message: String) {
        val newMessage = Message(
            userId = userId,
            messageId = getMessageId(),
            text = message,
            read = false,
            isIncoming = true
        )

        val newChat = chatList
            .firstOrNull { it.userId == userId && it.receiverId == receiverId }
            ?.let { chat -> chat.copy(messages = chat.messages + newMessage) }
            ?: Chat(
                userId = userId,
                receiverId = receiverId,
                chatId = getChatId(),
                messages = mutableListOf(newMessage)
            )

        chatList.removeIf { newChat.chatId == it.chatId }
        chatList.add(newChat)

    }


    fun editMessage(chatId: Int, editedMessage: Message): Boolean {
        return try {
            chatList.single { it.chatId == chatId }
                .messages.single { it.messageId == editedMessage.messageId }
                .text = editedMessage.text
            true
        } catch (ex: NoSuchElementException) {
            println("Message not found")
            false
        }
    }


    fun deleteMessage(chatId: Int, messageToDelete: Message): Boolean {
        return try {
            val messages = chatList.single { it.chatId == chatId}.messages as MutableList<Message>
            messages.remove(messageToDelete)

            chatList.firstOrNull { it.chatId == chatId }?.messages?.ifEmpty { deleteChat(chatId) }

            true
        } catch (ex: NoSuchElementException) {
            println("Message not found")
            false
        }
    }


    fun getUnreadChatsCount(userId: Int): Int {
        return chatList.filter { it.userId == userId }
            .map { chat -> chat.messages.filter { !it.read && it.isIncoming } }
            .count()
    }


}