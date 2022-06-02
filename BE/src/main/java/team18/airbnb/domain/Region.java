package team18.airbnb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    private String distance;
    private String regionImg;
    private String regionName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "region")
    private List<Accommodation> accommodations = new ArrayList<>();
}
