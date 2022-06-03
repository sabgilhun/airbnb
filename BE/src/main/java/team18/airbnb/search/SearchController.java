package team18.airbnb.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.region.RegionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final RegionService regionService;

    @GetMapping("/search")
    public ResponseEntity<List<LookAroundRegionDto>> searchRegion() {
        return ResponseEntity.ok(regionService.createLookAroundRegionDto());
    }
}
