package team18.airbnb.home;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import team18.airbnb.generalDto.LookAroundRegionDto;
import team18.airbnb.region.RegionService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "local")
class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Test
    @DisplayName("regionCount 갯수만큼 각 region 리스트에 객체가 추가되어야 한다.")
    void createRegion() {

        // when
        List<LookAroundRegionDto> homeRegions = regionService.createLookAroundRegionDto();
        List<LookAroundRegionDto> searchRegions = regionService.createLookAroundRegionDto();

        // then
        assertThat(homeRegions.size()).isEqualTo(8);
        assertThat(searchRegions.size()).isEqualTo(8);
    }
}
