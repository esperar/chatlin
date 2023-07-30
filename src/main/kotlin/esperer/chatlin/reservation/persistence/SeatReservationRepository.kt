package esperer.chatlin.reservation.persistence

import org.springframework.stereotype.Repository

object SeatMemoryDatabase{
    private const val MAX_SEAT_SIZE = 100
    private val seat = Array(MAX_SEAT_SIZE) { false }

    fun reserveSeat(id: Int){
        if(seat[id]) throw Exception("이미 예약되었습니다")
        seat[id] = true
    }

    fun cancelReservation(id: Int){
        if(!seat[id]) throw Exception("이미 취소 되었습니다.")
        seat[id] = false
    }
}

@Repository
class SeatReservationRepository {
    fun reserveSeat(id: Int){
        SeatMemoryDatabase.reserveSeat(id)
    }

    fun cancelReservation(id: Int){
        SeatMemoryDatabase.cancelReservation(id)
    }
}