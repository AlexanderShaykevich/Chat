data class Chat(
    val userId: Int,
    val receiverId: Int,
    val chatId: Int = 0,
    val messages: List<Message>,
    ) {

}