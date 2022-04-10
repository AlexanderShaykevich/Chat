data class Message(
    val userId: Int,
    val receiverId: Int,
    val messageId: Int,
    var chatId: Int,
    val text: String,
    val date: Int,
    var read: Boolean,
    val isIncoming: Boolean
) {
}