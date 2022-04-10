import org.junit.Test

import org.junit.Assert.*
import kotlin.math.exp

class ChatServiceTest {

    @Test
    fun createChat() {
        val service = ChatService()

        val expected = 1

        val result = service.createChat(Chat(1, 1, mutableListOf()))

        assertEquals(expected, result)
    }

    @Test
    fun deleteChatTrue() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))

        val result = service.deleteChat(1)

        assertTrue(result)
    }

    @Test
    fun deleteChatFalse() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))

        val result = service.deleteChat(2)

        assertFalse(result)
    }

    @Test
    fun getChats() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))

        val result = service.getChats(1).isNotEmpty()

        assertTrue(result)
    }

    @Test
    fun getChatsEmpty() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))

        val result = service.getChats(2).isNotEmpty()

        assertFalse(result)
    }

    @Test
    fun getMessagesFromChatTrue() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))
        service.createMessage(1, Message(1, 2, 0, 1, " ", 1, true, true))

        val result = service.getMessagesFromChat(1,1, 1).isNotEmpty()

        assertTrue(result)
    }

    @Test(expected = NoSuchElementException::class)
    fun getMessagesFromChatThrows() {
        val service = ChatService()

        service.getMessagesFromChat(1,1, 1)
    }

    @Test
    fun createMessage() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, true, true))

        val result = service.chatList.isNotEmpty()

        assertTrue(result)

    }

    @Test(expected = ChatNotFoundException::class)
    fun createMessageThrowsEx() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 1, " ", 1, true, true))

    }

    @Test
    fun editMessageTrue() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, true, true))

        val result = service.editMessage(Message(1, 2, 1, 1, " ", 1, true, true))

        assertTrue(result)
    }

    @Test
    fun editMessageFalse() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, true, true))

        val result = service.editMessage(Message(1, 2, 2, 1, " ", 1, true, true))

        assertFalse(result)
    }

    @Test
    fun deleteMessageTrue() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, true, true))

        val result = service.deleteMessage(Message(1, 2, 1, 1, " ", 1, true, true))

        assertTrue(result)
    }

    @Test
    fun deleteMessageFalse() {
        val service = ChatService()
        service.createChat(Chat(1, 1, mutableListOf()))

        val result = service.deleteMessage(Message(1,2,1,1," ",1,true,true))

        assertTrue(result)
    }

    @Test
    fun getUnreadChatsCount() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, false, true))

        val expected = 1

        val result = service.getUnreadChatsCount(1)

        assertEquals(expected, result)
    }

    @Test
    fun getUnreadChatsCountZero() {
        val service = ChatService()
        service.createMessage(1, Message(1, 2, 0, 0, " ", 1, true, true))

        val expected = 0

        val result = service.getUnreadChatsCount(2)

        assertEquals(expected, result)
    }

}