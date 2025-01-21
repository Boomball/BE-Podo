package be.podo.domain.seat.service;

import be.podo.domain.seat.dao.SeatDAO;
import be.podo.domain.seat.entity.Seat;
import java.util.List;
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

}
