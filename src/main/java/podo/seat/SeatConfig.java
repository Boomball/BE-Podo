package podo.seat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import podo.seat.constace.SeatStatus;
import podo.seat.dao.SeatDAO;
import podo.seat.entity.Seat;

@Configuration
public class SeatConfig {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 100;

    @Bean
    public CommandLineRunner loadInitialData(SeatDAO seatDAO) {
        return args -> {
            for (int i = MIN_ID; i <= MAX_ID; i++) {
                Seat seat = new Seat(
                        (long) i,
                        (char) ('A' + (i - 1) / 10),
                        i % 10 == 0 ? 10 : i % 10,
                        SeatStatus.AVAILABLE,
                        null
                );
                seatDAO.save(seat);
            }
        };
    }
}
