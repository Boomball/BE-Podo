package be.podo.domain.seat.controller;

import be.podo.domain.seat.entity.Seat;
import be.podo.domain.seat.service.SeatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    // 선택 좌석 선택
//    @PostMapping("/select")
//    public SeatResponseDTO selectCell(@RequestBody SeatRequestDTO cellRequest) {
//
//    }

//    // 선택 좌석 선택취소
//    @PostMapping("/deselect")
//    public SeatResponseDTO deselectSeat(@RequestBody SeatRequestDTO request) {
//
//    }


}
