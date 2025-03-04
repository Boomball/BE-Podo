package podo.user.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import podo.seat.dao.SeatDAO;
import podo.seat.entity.Seat;
import podo.user.dao.UserDAO;
import podo.user.dto.LoginRequest;
import podo.user.entity.User;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final SeatDAO seatDAO;

    public UserService(UserDAO userDAO, SeatDAO seatDAO) {
        this.userDAO = userDAO;
        this.seatDAO = seatDAO;
    }

    // 사용자 등록 (로그인 기능 개발 완료로 인해 추후 삭제 예정)
    @Transactional
    public void createUser(String userName) {
        if (!userDAO.existsById(userName)) {
            User user = new User(userName, "", new ArrayList<>());
            userDAO.save(user);
        }
    }

    // 회원가입
    @Transactional
    public void create(LoginRequest loginRequest) {
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();

        if (!userDAO.existsById(name)) {
            User user = new User(name, password, new ArrayList<>());
            userDAO.save(user);
        }
    }

    @Transactional
    public String login(LoginRequest loginRequest) {
        String name = loginRequest.getName();

        if (userDAO.existsById(name)) {
            User user = userDAO.findById(name)
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 사용자입니다."));
            return user.getName();
        }
        return "";
    }

    // 좌석 예약
    @Transactional
    public void reserveSeat(String userName, Long seatId) {
        User user = userDAO.findById(userName)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 사용자를 찾을 수 없습니다."));
        Seat seat = seatDAO.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 좌석을 찾을 수 없습니다."));

        seat.reserve();     // 좌석 예약
        user.addSeat(seat); // 유저에게 좌석 추가

        userDAO.save(user);
        seatDAO.save(seat);
    }

    // 선택한 좌석 표시
    @Transactional
    public List<Seat> getReservedSeats(String userName) {
        User user = userDAO.findById(userName)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 사용자를 찾을 수 없습니다."));
        return user.getSeats();
    }
}