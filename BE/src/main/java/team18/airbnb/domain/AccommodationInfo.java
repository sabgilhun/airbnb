package team18.airbnb.domain;

import javax.persistence.*;

@Embeddable
public class AccommodationInfo {

    private int bedCount;
    private int bathCount;
    private int maxGuest;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

}
