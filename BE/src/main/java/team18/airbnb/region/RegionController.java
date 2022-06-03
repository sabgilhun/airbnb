package team18.airbnb.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.region.dto.AccommodationByConceptDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> home() {

        List<AccommodationByConceptDto> accommodationByConcepts = regionService.createAccommodationByConceptDto();
        List<LookAroundRegionDto> regions = regionService.createLookAroundRegionDto();

        Map<String, Object> homeData = new HashMap<>();
        homeData.put("Accommodation" ,accommodationByConcepts);
        homeData.put("Region" ,regions);

        return ResponseEntity.ok(homeData);
    }
}
