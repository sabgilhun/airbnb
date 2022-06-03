package team18.airbnb.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team18.airbnb.domain.Reservation;
import team18.airbnb.reservation.dto.UserReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findByUsername() {
        return reservationRepository.findByUsername();
    }

    public List<UserReservationDto> createUserReservationDto(List<Reservation> findReservationsByUsername) {
        return findReservationsByUsername.stream()
                .map(UserReservationDto::new)
                .collect(Collectors.toList());
    }
}
