package be.podo.domain.seat;

import be.podo.domain.seat.constace.SeatStatus;
import be.podo.domain.seat.dao.SeatDAO;
import be.podo.domain.seat.entity.Seat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeatConfig {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 100;

    @Bean
    public CommandLineRunner loadInitialData(SeatDAO seatDAO) {
        return args -> {
            for (int i = MIN_ID; i <= MAX_ID; i++) {
                Seat seat = new Seat();
                seat.setId((long) i);
                seat.setSeatRow((char) ('A' + (i - 1) / 10));  // A부터 시작하는 행
                seat.setSeatColumn(i % 10 == 0 ? 10 : i % 10); // 열 (1~10)
                seat.setStatus(SeatStatus.AVAILABLE);          // 기본 상태는 AVAILABLE

                seatDAO.save(seat);
            }
        };
    }
}
