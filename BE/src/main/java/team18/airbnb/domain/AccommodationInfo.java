package team18.airbnb.domain;

import javax.persistence.*;

@Embeddable
public class AccommodationInfo {

    private int nBed;
    private int nBath;
    private int maxGuest;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

}
