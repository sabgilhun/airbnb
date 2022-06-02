package team18.airbnb.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DiscountPolicy {

    WEEK(4.0), MONTH(8.0), YEAR(16.0);

    private final double percent;
}
