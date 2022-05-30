package team18.airbnb.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccommodationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "accommodationInfo", fetch = FetchType.LAZY)
    private Accommodation accommodation;

    private String description;
    private int numberOfBed;
    private int numberOfBath;
    private int discountOfWeek;
    private int maximumNumberOfGuests;
    private String accommodationMainImg;
    private String accommodationDetailImg;
}
