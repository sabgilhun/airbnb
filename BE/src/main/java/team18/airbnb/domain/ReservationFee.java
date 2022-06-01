package team18.airbnb.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class ReservationFee {

    // 일 수에 따른 총 금액
    private int totalAmountOfDay;

    // 청소비
    private int cleaningFee;

    // 서비스 수수료
    private int serviceFee;

    // 숙박세와 수수료
    private int roomCharge;

    // 총 예약 확정 금액
    private int totalAmountOfReservation;

    // 주 단위 요금 할인
    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

}