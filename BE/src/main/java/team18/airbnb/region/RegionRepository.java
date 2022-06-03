package team18.airbnb.region;

import org.springframework.data.jpa.repository.JpaRepository;

import team18.airbnb.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
