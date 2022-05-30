package team18.airbnb.home;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import team18.airbnb.domain.Region;
import team18.airbnb.home.dto.AccommodationByConceptDto;
import team18.airbnb.home.dto.LookAroundRegionDto;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;

    public List<AccommodationByConceptDto> createAccommodationByConcept() {
        List<AccommodationByConceptDto> accommodations = new ArrayList<>();

        AccommodationByConceptDto first =
                new AccommodationByConceptDto("url", "자연 생활을 만끽할 수 있는 숙소");

        AccommodationByConceptDto second =
                new AccommodationByConceptDto("url", "독특한 공간");

        accommodations.add(first);
        accommodations.add(second);

        return accommodations;
    }

    public List<LookAroundRegionDto> createRegion() {
        List<Region> allRegion = homeRepository.findAll();

        return allRegion.stream()
                .limit(4)
                .map(LookAroundRegionDto::new)
                .collect(Collectors.toList());
    }
}
