package team18.airbnb.domain;

import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float startPoint;
    private int nReview;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    @JoinColumn(name = "reservation_id")
    private List<Reservation> reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Embedded
    private AccommodationAddress accommodationAddress;

    @Embedded
    private AccommodationInfo accommodationInfo;

    public Accommodation(float startPoint, int nReview, String description,
                         AccommodationAddress accommodationAddress,
                         AccommodationInfo accommodationInfo,
                         Region region) {

        this.startPoint = startPoint;
        this.nReview = nReview;
        this.description = description;
        this.accommodationAddress = accommodationAddress;
        this.accommodationInfo = accommodationInfo;

        if (region != null) {
            changeRegion(region);
        }
    }

    private void changeRegion(Region region) {
        this.region = region;
        region.getAccommodations().add(this);
    }
}
