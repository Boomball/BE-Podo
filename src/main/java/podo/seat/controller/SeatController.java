package podo.seat.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import podo.seat.entity.Seat;
import podo.seat.service.SeatService;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // 전체 좌석 조회
    @GetMapping()
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

}
