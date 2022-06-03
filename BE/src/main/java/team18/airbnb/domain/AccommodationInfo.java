package team18.airbnb.domain;

import javax.persistence.*;

import lombok.Getter;

@Embeddable
@Getter
public class AccommodationInfo {

    private int bedCount;
    private int bathCount;
    private int maxGuest;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

}
