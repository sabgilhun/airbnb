package team18.airbnb.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class AccommodationAddress {

    private String si;
    private String gun;
    private String gu;
    private String dong;
    private String eup;
}
