package team18.airbnb.reservation.dto;

import lombok.Getter;
import team18.airbnb.domain.AccommodationAddress;
import team18.airbnb.domain.Reservation;

import java.time.LocalDateTime;

@Getter
public class UserReservationDto {

    private final Reservation reservation;
    private final LocalDateTime checkInTime;
    private final LocalDateTime checkoutTime;
    private final AccommodationAddress accommodationAddress;
    private final String accommodationName;

    public UserReservationDto(Reservation reservation) {

        this.reservation = reservation;
        this.checkInTime = reservation.getCheckInTime();
        this.checkoutTime = reservation.getCheckoutTime();
        this.accommodationAddress = reservation.getAccommodation().getAccommodationAddress();
        this.accommodationName = reservation.getAccommodation().getName();
    }
}
