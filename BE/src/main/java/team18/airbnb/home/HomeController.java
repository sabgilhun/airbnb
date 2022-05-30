package team18.airbnb.home;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.home.dto.AccommodationByConceptDto;
import team18.airbnb.home.dto.LookAroundRegionDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> home() {

        List<AccommodationByConceptDto> accommodationByConcepts = homeService.createAccommodationByConcept();
        List<LookAroundRegionDto> regions = homeService.createRegion();

        Map<String, Object> homeData = new HashMap<>();
        homeData.put("Accommodation" ,accommodationByConcepts);
        homeData.put("Region" ,regions);

        return ResponseEntity.ok(homeData);
    }
}
