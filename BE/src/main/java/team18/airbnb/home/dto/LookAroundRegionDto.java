package team18.airbnb.home.dto;

import lombok.Getter;
import team18.airbnb.domain.Region;

@Getter
public class LookAroundRegionDto {

    private final String regionName;
    private final String regionImg;
    private final String distance;

    public LookAroundRegionDto(Region region) {
        this.regionName = region.getRegionName();
        this.regionImg = region.getRegionName();
        this.distance = region.getDistance();
    }
}
