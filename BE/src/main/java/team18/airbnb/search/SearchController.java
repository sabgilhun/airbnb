package team18.airbnb.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.home.HomeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final HomeService homeService;

    @GetMapping("/search/{regionCount}")
    public ResponseEntity<List<LookAroundRegionDto>> searchRegion(@PathVariable int regionCount) {
        return ResponseEntity.ok(homeService.createRegion(regionCount));
    }
}
