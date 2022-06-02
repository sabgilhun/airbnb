package team18.airbnb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private Long id;

    private float startPoint;
    private int nReview;
    private String description;
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accommodation")
    private List<Reservation> reservation = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Embedded
    private AccommodationAddress accommodationAddress;

    @Embedded
    private AccommodationInfo accommodationInfo;

    public Accommodation(float startPoint, int nReview, String description, String name,
                         AccommodationAddress accommodationAddress,
                         AccommodationInfo accommodationInfo,
                         Region region) {

        this.startPoint = startPoint;
        this.nReview = nReview;
        this.description = description;
        this.name = name;
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
