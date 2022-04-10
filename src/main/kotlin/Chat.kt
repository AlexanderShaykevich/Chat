data class Chat(
    val userId: Int,
    val chatId: Int = 0,
    val messages: MutableList<Message>,
    ) {

}