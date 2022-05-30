package team18.airbnb.home;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.airbnb.domain.Region;

public interface HomeRepository extends JpaRepository<Region, Long> {
}
