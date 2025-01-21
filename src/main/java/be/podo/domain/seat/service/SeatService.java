package be.podo.domain.seat.service;

import be.podo.domain.seat.constace.SeatStatus;
import be.podo.domain.seat.dao.SeatDAO;
import be.podo.domain.seat.entity.Seat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatDAO seatDAO;

    @Autowired
    public SeatService(SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    // 전체 좌석 조회
    public List<Seat> getAllSeats() {
        return seatDAO.findAll();
    }

    // 좌석 선택
    public Optional<Seat> selectSeat(Long seatId) {
        Optional<Seat> seatOptional = seatDAO.findById(seatId);

        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                seat.setStatus(SeatStatus.RESERVED);
                seatDAO.save(seat);
                return Optional.of(seat);
            }
        }
        return Optional.empty();
    }

}
