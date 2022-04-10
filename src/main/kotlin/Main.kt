fun main() {
    val service = ChatService()
    service.createMessage(1, Message(1,2,0,0,"Hello",1,false,true))
    service.createMessage(1, Message(1,2,0,1,"Hello2",1,false,true))
    service.createMessage(1, Message(1,2,0,1,"Hello3",1,false,false))
    service.createMessage(1, Message(1,2,0,1,"Hello4",1,false,true))
    println(service.chatList)


//    service.editMessage(Message(1,2,1,1,"Hello999",1,false,true))
//    println(service.chatList)
//    service.createMessage(Message(1,2,0,1,"Hello3",1,false,false))
//    println(service.chatList)

//    println(service.getUnreadChatsCount(1))
//
//    println(service.getChats(1))
//
//    service.deleteChat(3)
//
//    println(service.getChats(1))

//    println(service.deleteMessage(1, Message(1,2,1,1,"Hello",1,false,true)))
//
//    println(service.chatList)

    println(service.getMessagesFromChat(1,4,2))





}