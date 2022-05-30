package team18.airbnb.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_info_id")
    private AccommodationInfo accommodationInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_price_id")
    private AccommodationPrice accommodationPrice;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    private String accommodationName;
    private String accommodationAddress;
}
