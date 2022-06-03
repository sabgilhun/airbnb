package team18.airbnb.region;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import team18.airbnb.domain.Region;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.region.dto.AccommodationByConceptDto;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<AccommodationByConceptDto> createAccommodationByConceptDto() {
        List<AccommodationByConceptDto> accommodations = new ArrayList<>();

        AccommodationByConceptDto first =
                new AccommodationByConceptDto("url", "자연 생활을 만끽할 수 있는 숙소");

        AccommodationByConceptDto second =
                new AccommodationByConceptDto("url", "독특한 공간");

        accommodations.add(first);
        accommodations.add(second);

        return accommodations;
    }

        public List<LookAroundRegionDto> createLookAroundRegionDto() {
            List<Region> allRegion = regionRepository.findAll();

            return allRegion.stream()
                    .map(LookAroundRegionDto::new)
                .collect(Collectors.toList());
    }
}
