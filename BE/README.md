# Airbnb 프로젝트(백엔드)

## 구성원

[@tany](https://github.com/juni8453)
[@geombong](https://github.com/geombong)

### 프로젝트 작업 내용

## DB요구사항

<details>
<summary>📌DB요구사항 분석</summary>

- 회원 가입을 위해 아이디, 전화번호, 생년월일, 주소를 입력해야한다.
- 회원은 회원 아이디로 식별한다.
- 숙소에 대한 지역, 주소, 가격, 예약상태, 이름, 설명, 유형, 호스트 이름, 침대와 욕실 개수, 할인률, 이미지 정보를 가지고 있어야한다.
- 회원은 여러 숙소를 예약할 수 있고, 하나의 숙소는 하나의 예약만 가능하다.
- 회원은 숙소를 주소와 지역으로 검색이 가능하다.
- 회원이 예약을 하게되면 체크인, 체크아웃, 게스트수 정보를 유지해야한다.
- 회원은 리뷰를 작성할 수 있다.
- 리뷰에는 리뷰번호, 별점, 내용, 작성자 정보가 있다.
- 리뷰는 리뷰 번호로 식별한다.
- 회원은 여러 리뷰를 남길 수 있고, 리뷰하나에는 하나의 회원만 존재한다.
- 하나의 숙소에는 여러 리뷰가 있을수 있고, 하나의 리뷰에는 하나의 숙소만 존재한다.
- 하나의 지역에는 여러 숙소가 존재한다.
- 숙소 가격에는 청소비, 1박당 가격이 있다.

</details>

## ERD

<details>
<summary>📌ERD 초안</summary>

![E-R다이어그램](https://user-images.githubusercontent.com/79444040/170203077-182d0523-126d-4ef7-bf13-e2959fd7c141.png)
<img width="900" alt="스크린샷 2022-05-25 오후 4 29 53" src="https://user-images.githubusercontent.com/79444040/170205968-a5d65211-1077-4ab3-9cc5-ef006a0576fa.png">

</details>

<details>
<summary>📌ERD 1차 수정안</summary>

<img width="658" alt="airbnb_erd_1_2" src="https://user-images.githubusercontent.com/78953393/171772998-39d1a376-2dd9-48e8-bcff-1830d40da472.png">

<img width="794" alt="airbnb_erd_1" src="https://user-images.githubusercontent.com/78953393/171773010-3130eb63-a828-41a4-b9e0-7baea3c6bfde.png">

</details>

## 인프라

<details>
<summary>📌인프라 구상 및 테스트</summary>

### ✏️ AWS 인프라 구상도
![Airbnb docker 전 최종 인프라 drawio](https://user-images.githubusercontent.com/79444040/170923420-e29e2fc8-faf6-41b6-bee6-d96fab02d911.png)

### 작업 내용
- [x] AWS 인프라 구상도 작성 (draw.io tool 사용)
- [x] Airbnb 프로젝트에서 사용할 VPC 사설 네트워크 망 생성
- [x] Public 서브넷 1개, Private 서브넷 2개 생성
- [x] 인터넷과의 통신을 위한 인터넷 게이트웨이 생성 후 VPC 에 연결
- [x] 각 서브넷에 연결할 라우팅 테이블 3개 생성 후 각 서브넷과 연결
- [x] NAT 인스턴스, Bastion 인스턴스, WEB Server 인스턴스, WAS 인스턴스, MySQL 인스턴스 생성
- [x] 각 인스턴스 보안그룹을 따로 생성해서 인바운드 규칙 추가
- [x] Bastion 인스턴스를 통해 Private 서브넷에 접근하기 위해 ssh-agent 를 사용해 Private Key 를 메모리에 캐싱
- [x] 캐싱된 Private Key 를 가지고 Bastion 인스턴스에서 Private 서브넷 접속 테스트
- [x] README 파일에 업로드 예정

### 🚧 접속 Test
- [x] Bastion Instance 접속 성공

  <img width="653" alt="스크린샷 2022-05-30 오후 2 37 33" src="https://user-images.githubusercontent.com/79444040/170924030-914e247d-4cc7-4eb0-8013-61df9cd45b07.png">

- [x] Bastion Instance 접속 후 Private 서브넷 접속 성공

  <img width="601" alt="스크린샷 2022-05-30 오후 2 39 08" src="https://user-images.githubusercontent.com/79444040/170924303-202aa9bc-4aca-454a-94f7-8fe8764de606.png">

</details>

## PR리뷰

<details>
<summary>📌1주차 리뷰</summary>

### 1주차 리뷰 수정내용

- [x] 어노테이션 한줄에 여러개 작성한거 수정
- [x] PK에 명시해놓은 컬럼명 제거
- [x] 오타 수정
- [x] 참조하고 있는 필드명 변경
- [x] 단반향으로 설계되어 있는 매핑 양방향 고려해보기

### 고민한 부분

- 양뱡향 연관관계 `편의메서드`가 `OneToOne`에서 필요한가?

```text
  편의 메서드를 사용하는 이유
    - 양뱡향 연관관계 매핑 시 JPA 입장에서 보았을때 서로 조회할 수 있게 객체를 필드로 정의하고 연관관계 주인만 잘 설정해 준다면 문제가 없다. 
	하지만, 연관관계 주인과 순수 객체를 모두 동기화 해주기 위한 작업이 반드시 필요하다. 편의 메서드를 사용하게 되면 실수를 방지 할 수 있다.
```

- 양방향 매핑 편의메서드 예제

```java
private void changeRegion(Region region) {
    this.region = region;
    region.getAccommodations().add(this);
}
```

- `set`이라는 네이밍은 너무 관례적이기 때문에 `change`라고 하여 내부 로직이 있음을 나타내준다.

</details>

<details>
<summary>📌2주차 리뷰</summary>

### 2주차 리뷰 수정내용

- [x] 불필요한 print문 제거
- [x] 앞에 n이 붙은 변수 네이밍 수정
- [x] 서브 이미지를 임베디드 타입이 아닌 하나의 엔티티로 변경
- [x] 이름과 작업이 일치하지 않는 메서드네이밍 수정
- [x] prefix와 타입이 일치하지 않은 Repository 네이밍 수정
- [x] homeController에서 데이터 반환용으로 사용중인 map을 객체로 변경

</details>
