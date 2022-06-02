package team18.airbnb.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team18.airbnb.domain.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select r from Reservation r join fetch r.accommodation where r.username =:username")
    List<Reservation> findByUsername(@Param("username") String username);
}
