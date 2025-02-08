package podo.user.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import podo.seat.entity.Seat;
import podo.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 등록
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody String userName) {
        try {
            userService.createUser(userName);
            return ResponseEntity.ok("사용자가 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 등록에 실패했습니다: " + e.getMessage());
        }
    }

    // 좌석 예약
    @PostMapping("/{userName}/reserve/{seatId}")
    public ResponseEntity<String> reserveSeat(@PathVariable String userName, @PathVariable Long seatId) {
        try {
            userService.reserveSeat(userName, seatId);
            return ResponseEntity.ok("좌석이 예약되었습니다.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 선택한 좌석 표시
    @GetMapping("/{userName}/seats")
    public ResponseEntity<List<Seat>> getUserSeats(@PathVariable String userName) {
        List<Seat> seats = userService.getReservedSeats(userName);
        return ResponseEntity.ok(seats);
    }
}