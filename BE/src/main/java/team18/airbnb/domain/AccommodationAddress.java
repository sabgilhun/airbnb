package team18.airbnb.domain;

import javax.persistence.Embeddable;

@Embeddable
public class AccommodationAddress {

    // 시, 군, 구, 동, 읍 순
    private String si;
    private String gun;
    private String gu;
    private String dong;
    private String eup;
}
