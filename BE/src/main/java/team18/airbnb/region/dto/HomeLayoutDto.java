package team18.airbnb.region.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team18.airbnb.generalDto.LookAroundRegionDto;

@Getter
@RequiredArgsConstructor
public class HomeLayoutDto {

    private final List<AccommodationByConceptDto> accommodationByConceptDtos;
    private final List<LookAroundRegionDto> lookAroundRegionDtos;
}
