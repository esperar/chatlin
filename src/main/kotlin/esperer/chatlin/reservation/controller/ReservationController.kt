package esperer.chatlin.reservation.controller

import esperer.chatlin.chat.listener.WebSocketEventListener
import esperer.chatlin.reservation.data.Seat
import esperer.chatlin.reservation.persistence.SeatReservationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
class ReservationController(
    private val seatReservationRepository: SeatReservationRepository,
    private val simpMessagingTemplate: SimpMessagingTemplate
) {

    private val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    @RequestMapping("/api/v1/reservation/{id}")
    fun reservationSeat(@PathVariable id: Int) {
        try {
            seatReservationRepository.reserveSeat(id)
            simpMessagingTemplate.convertAndSend("/seat", Seat(id, true))
            logger.info("좌석번호 $id 예약되었습니다.")
        } catch (e: Exception){
            throw RuntimeException(e)
        }
    }

    @RequestMapping("/api/v1/reservation/cancel/{id}")
    fun cancelReservation(@PathVariable("id") id: Int){
        seatReservationRepository.cancelReservation(id)
        simpMessagingTemplate.convertAndSend("/seat", Seat(id, false))
        println("좌석번호 $id 예약 취소 되었습니다")
    }

}