class ChatService {
    private var uniqueChatId = 1
    private var uniqueMessageId = 1
    var chatList = mutableListOf<Chat>()

    fun createChat(chat: Chat): Int {
        val newChat = chat.copy(chatId = uniqueChatId)
        chatList.add(newChat)
        uniqueChatId++
        return newChat.chatId
    }

    fun deleteChat(chatId: Int): Boolean {
        if (chatList.removeIf { it.chatId == chatId }) {
            return true
        }
        return false
    }

    fun getChats(userId: Int): List<Chat> {
        val chatSearchList = chatList.filter { it.userId == userId }

        return if (chatSearchList.isNotEmpty()) {
            chatSearchList
        } else {
            println("There are no messages")
            emptyList()
        }
    }


    fun getMessagesFromChat(chatId: Int, messageId: Int, amount: Int): List<Message> {
        val result = chatList.single { it.chatId == chatId  }.messages
            .filter { it.messageId >= messageId }
            .take(amount)

        for (chat in chatList) {
            if (chatId == chatId) {
                for (message in chat.messages) {
                    message.read = true
                }
            }
        }
        return result
    }


    fun createMessage(userId: Int, message: Message) {
        if (message.chatId == 0) {
            val newMessage = message.copy(userId = userId, messageId = uniqueMessageId)
            val chatMessageList = mutableListOf(newMessage)
            newMessage.chatId = createChat(Chat(message.userId, 0, chatMessageList))
            uniqueMessageId++
        } else {
            val newMessage = message.copy(userId = userId, messageId = uniqueMessageId)
            val list = chatList.filter { it.chatId == message.chatId }
            if (list.isNotEmpty()) {
                list[0].messages.add(newMessage)
                uniqueMessageId++
            } else {
                throw ChatNotFoundException()
            }
        }
    }


    fun editMessage(editedMessage: Message): Boolean {
        for (chat in chatList) {
            if (chat.chatId == editedMessage.chatId) {
                for ((index, message) in chat.messages.withIndex()) {
                    if (message.messageId == editedMessage.messageId) {
                        chat.messages[index] = message.copy(text = editedMessage.text)
                        return true
                    }
                }
            }
        }
        return false
    }

    fun deleteMessage(messageToDelete: Message): Boolean {
        for (chat in chatList) {
            if (chat.chatId == messageToDelete.chatId) {
                if (chat.messages.remove(messageToDelete)) {
                    if (chat.messages.isEmpty()) {
                        deleteChat(chat.chatId)
                    }
                }
            }
            return true
        }
        return false
    }


    fun getUnreadChatsCount(userId: Int): Int {
        return chatList.filter { it.userId == userId }
            .map { chat -> chat.messages.filter { !it.read && it.isIncoming } }
            .filter { it.isNotEmpty() }.count()

    }


}
