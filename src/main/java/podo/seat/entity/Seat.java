package podo.seat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import podo.seat.constace.SeatStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    private Long id;

    @Column(nullable = false)
    private Character seatRow;

    @Column(nullable = false)
    private Integer seatColumn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public void changeStatus() {
        if (this.status == SeatStatus.AVAILABLE) {
            this.status = SeatStatus.RESERVED;
        } else if (this.status == SeatStatus.RESERVED) {
            this.status = SeatStatus.AVAILABLE;
        }
    }

}
