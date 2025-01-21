package be.podo.domain.seat.entity;

import be.podo.domain.seat.constace.SeatStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {

    @Id
    private Long id;

    @Column(nullable = false)
    private Character seatRow;

    @Column(nullable = false)
    private Integer seatColumn;

    @Column(nullable = false)
    private SeatStatus status;

}
