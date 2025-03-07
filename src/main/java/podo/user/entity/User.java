package podo.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import podo.seat.entity.Seat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String name;

    // 편의를 위해 해쉬값은 사용하지 않겠음
    private String password;

    @OneToMany(mappedBy = "owner")
    @OrderBy("seatRow ASC, seatColumn ASC")
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        seat.setOwner(this.name);
        this.seats.add(seat);
    }

}
