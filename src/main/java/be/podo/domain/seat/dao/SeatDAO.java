package be.podo.domain.seat.dao;

import be.podo.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDAO extends JpaRepository<Seat, Long> {
}
