insert into region(distance, region_img, region_name) values ('차로 30분거리', '서울이미지', '서울');
insert into region(distance, region_img, region_name) values ('차로 4시간거리', '광주이미지', '광주');
insert into region(distance, region_img, region_name) values ('차로 30분거리', '의정부이미지', '의정부');
insert into region(distance, region_img, region_name) values ('차로 45분거리', '수원이미지', '수원');
insert into region(distance, region_img, region_name) values ('차로 3.5시간거리', '이미지', '대구');
insert into region(distance, region_img, region_name) values ('차로 4.5시간시간거리', '대구이미지', '울산');
insert into region(distance, region_img, region_name) values ('차로 2시간거리', '대전이미지', '대전');
insert into region(distance, region_img, region_name) values ('차로 30분거리', '부천이미지', '부천');

insert into user(username, password, address, birthday, phone_number) values ('tany', '8888', 'juni8453@naver.com', '1996-12-24', '010-5592-9710');

insert into accommodation(dong, eup, gu, gun, si, accommodation_type, max_guest, n_bath, n_bed, description, n_review, name, start_point, region_id)
                                                                                    VALUES ('양재동', null, '서초구', null, '서울특별시', 'ONE_ROOM', 3, 1, 1, '설명', '130', '신라호텔', '4.80', '1');

insert into reservation(check_in_time, checkout_time, max_price, min_price, n_adult, n_child, n_infant, cleaning_fee, discount_policy, room_charge, service_fee, total_amount_of_day, total_amount_of_reservation, accommodation_id, username)
                                                                                    VALUES ('2022-05-23', '2022-06-10', 1000000, 50000, 2, 1, 0, 25996, 'WEEK', 82953, 8188, 1322396, 1488195, 1, 'tany');
