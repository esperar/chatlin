package esperer.chatlin.chat.model

data class ChatMessage(
    var type: MessageType,
    var content: String,
    var sender: String
)


