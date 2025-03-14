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
import podo.user.dto.LoginRequest;
import podo.user.dto.LoginResponse;
import podo.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 및 회원가입
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        userService.create(loginRequest);
        String userName = userService.login(loginRequest);
        if (userName.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(""));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponse(userName));
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