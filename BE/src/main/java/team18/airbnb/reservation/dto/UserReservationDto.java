package team18.airbnb.reservation.dto;

import lombok.Getter;
import team18.airbnb.domain.AccommodationAddress;
import team18.airbnb.domain.Reservation;

import java.time.LocalDateTime;

@Getter
public class UserReservationDto {

    private final LocalDateTime checkInTime;
    private final LocalDateTime checkoutTime;
    private final AccommodationAddress accommodationAddress;
    private final String accommodationName;
    private final int nAdult;
    private final int nChild;
    private final int nInfant;
    private final int totalAmountOfReservation;
    private final int totalGuest;

    public UserReservationDto(Reservation reservation) {
        this.checkInTime = reservation.getCheckInTime();
        this.checkoutTime = reservation.getCheckoutTime();
        this.accommodationAddress = reservation.getAccommodation().getAccommodationAddress();
        this.accommodationName = reservation.getAccommodation().getName();
        this.nAdult = reservation.getNAdult();
        this.nChild = reservation.getNChild();
        this.nInfant = reservation.getNInfant();
        this.totalAmountOfReservation = reservation.getReservationFee().getTotalAmountOfReservation();
        this.totalGuest = getNAdult() + getNChild() + getNInfant();
    }
}

