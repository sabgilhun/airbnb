package team18.airbnb.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    @Column(nullable = false)
    private LocalDateTime checkoutTime;

    @Column(nullable = false)
    private int nAdult;

    @Column(nullable = false)
    private int nChild;

    @Column(nullable = false)
    private int nInfant;

    @Column(nullable = false)
    private int maxPrice;

    @Column(nullable = false)
    private int minPrice;

    @Column(nullable = false)
    private String username;

    // 양방향 매핑일 때 무한루프 발생
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Embedded
    private ReservationFee reservationFee;

    public Reservation(LocalDateTime checkInTime,
                       LocalDateTime checkoutTime,
                       int nAdult,
                       int nChild,
                       int nInfant,
                       Accommodation accommodation,
                       ReservationFee reservationFee) {

        this.checkInTime = checkInTime;
        this.checkoutTime = checkoutTime;
        this.nAdult = nAdult;
        this.nChild = nChild;
        this.nInfant = nInfant;
        this.reservationFee = reservationFee;

        if (accommodation != null) {
            changeAccommodation(accommodation);
        }
    }

    private void changeAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
        accommodation.getReservation().add(this);
    }
}
