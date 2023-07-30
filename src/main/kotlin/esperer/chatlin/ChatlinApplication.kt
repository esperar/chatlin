package esperer.chatlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatlinApplication

fun main(args: Array<String>) {
    runApplication<ChatlinApplication>(*args)
}
