package team18.airbnb.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.domain.Reservation;
import team18.airbnb.reservation.dto.UserReservationDto;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations/{username}")
    public ResponseEntity<List<UserReservationDto>> myReservation(@PathVariable String username) {

        List<Reservation> findReservationsByUsername = reservationService.findByUsername(username);
        List<UserReservationDto> reservations = reservationService.createReservations(findReservationsByUsername);

        return ResponseEntity.ok(reservations);
    }
}
