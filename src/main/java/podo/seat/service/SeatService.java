package podo.seat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import podo.seat.dao.SeatDAO;
import podo.seat.entity.Seat;

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
