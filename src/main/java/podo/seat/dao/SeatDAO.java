package podo.seat.dao;

import podo.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDAO extends JpaRepository<Seat, Long> {
}
