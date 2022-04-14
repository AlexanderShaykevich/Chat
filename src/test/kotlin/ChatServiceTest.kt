import org.junit.Test

import org.junit.Assert.*
import kotlin.math.exp

class ChatServiceTest {

    @Test
    fun deleteChatTrue() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.deleteChat(1)

        assertTrue(result)
    }

    @Test
    fun deleteChatFalse() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.deleteChat(2)

        assertFalse(result)
    }

    @Test
    fun getChats() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.getChats(1).isNotEmpty()

        assertTrue(result)
    }

    @Test (expected = ChatNotFoundException::class)
    fun getChatsThrowsEx() {
        val service = ChatService()
        service.createMessage(1,2," ")

        service.getChats(2)
    }

    @Test
    fun getMessagesFromChatTrue() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.getMessagesFromChat(1,1, 1).isNotEmpty()

        assertTrue(result)
    }

    @Test
    fun getMessagesFromChatEmpty() {
        val service = ChatService()

        service.getMessagesFromChat(1,1, 1)

        val result = service.getMessagesFromChat(1,1, 1).isEmpty()

        assertTrue(result)
    }

    @Test
    fun createMessage() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.chatList.isNotEmpty()

        assertTrue(result)

    }


    @Test
    fun editMessageTrue() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.editMessage(1, Message(1, 1, " ", false, true))

        assertTrue(result)
    }

    @Test
    fun editMessageFalse() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.editMessage(1, Message(1, 2, " ", false, true))

        assertFalse(result)
    }

    @Test
    fun deleteMessageTrue() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.deleteMessage(1, Message(1, 1, " ", false, true))

        assertTrue(result)
    }

    @Test
    fun deleteMessageFalse() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val result = service.deleteMessage(1, Message(1, 2, " ", false, true))

        assertTrue(result)
    }

    @Test
    fun getUnreadChatsCount() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val expected = 1

        val result = service.getUnreadChatsCount(1)

        assertEquals(expected, result)
    }

    @Test
    fun getUnreadChatsCountZero() {
        val service = ChatService()
        service.createMessage(1,2," ")

        val expected = 0

        val result = service.getUnreadChatsCount(2)

        assertEquals(expected, result)
    }

}