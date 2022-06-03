package team18.airbnb.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.region.dto.AccommodationByConceptDto;
import team18.airbnb.region.dto.HomeLayoutDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/main")
    public ResponseEntity<HomeLayoutDto> home() {

        List<AccommodationByConceptDto> accommodationByConceptDtos = regionService.createAccommodationByConceptDto();
        List<LookAroundRegionDto> lookAroundRegionDtos = regionService.createLookAroundRegionDto();

        HomeLayoutDto homeLayoutDto = new HomeLayoutDto(accommodationByConceptDtos, lookAroundRegionDtos);

        return ResponseEntity.ok(homeLayoutDto);
    }
}
