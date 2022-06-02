package team18.airbnb.search;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.airbnb.domain.Region;

public interface SearchRepository extends JpaRepository<Region, Long> {
}
