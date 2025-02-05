package podo.seat.controller;

import podo.seat.dto.SeatRequestDTO;
import podo.seat.dto.SeatResponseDTO;
import podo.seat.entity.Seat;
import podo.seat.service.SeatService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // 선택 좌석 선택
    @PostMapping("/select")
    public ResponseEntity<SeatResponseDTO> selectCell(@RequestBody SeatRequestDTO request) {
        Optional<Seat> seatOptional = seatService.selectSeat(request.getId());

        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            SeatResponseDTO response = new SeatResponseDTO(
                    seat.getId(), seat.getSeatRow(), seat.getSeatColumn(), seat.getStatus());
            return ResponseEntity.ok(response);
        } else {
            SeatResponseDTO response = new SeatResponseDTO(null, null, null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

//    // 선택 좌석 선택취소
//    @PostMapping("/deselect")
//    public SeatResponseDTO deselectSeat(@RequestBody SeatRequestDTO request) {
//
//    }


}
