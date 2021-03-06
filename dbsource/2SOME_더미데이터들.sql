-- 매니저 테이블 삽입---
INSERT INTO MANAGER(NO, ID, PWD, NAME, PHONE, EMAIL) 
VALUES(MANAGER_NO_SEQ.NEXTVAL, 'HONG01', '1234', '홍길동', '010-1234-5678', 'HONGG@NAVER.COM');
--------------------

--카테고리 테이블 삽입---
INSERT INTO CATEGORY VALUES (1, 'DRINK'); 
INSERT INTO CATEGORY VALUES (2, 'FOODS'); 
INSERT INTO CATEGORY VALUES (3, 'GOODS');
--------------------

--메뉴 테이블 삽입---
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '아메리카노', 3000, '진한 에스프레소와 뜨거운 물을 섞어 저희 카페의 깔끔하고 강렬한 에스프레소를 가장 부드럽게 잘 느낄 수 있습니다', 10, 150, 2, 0, 0, 1, 5);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '카페 라떼', 4000, '풍부하고 진한 농도의 에스프레소와 우유가 만나 고소함을 즐길 수 있습니다.', 110, 75, 9, 8, 6, 6, 75);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '돌체 라떼', 5700, '다른 커피 음료보다 더욱 커피의 맛과 향에 깔끔한 무지방 우유와 부드러운 돌체 시럽이 들어간 음료로 달콤고 진한 맛을 느낄 수 있습니다.', 175, 75, 28, 26, 3, 8, 130);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '카푸치노', 5000, '풍부하고 진한 에스프레소에 우유와 벨벳 같은 우유 거품이 1:1 비율로 어우러져 마무리된 음료입니다.', 90, 75, 7, 7, 4, 5, 60);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '에스프레소', 4000, '향기로운 크레마 층과 바디 층, 하트 층으로 이루어져 있으며, 입안 가득히 커피와 달콤한 카라멜 향을 느낄 수 있습니다.', 5, 75, 1, 0, 0, 1, 0);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '자바 칩 프라푸치노', 6300, '커피, 모카 소스, 진한 초콜릿 칩을 입안 가득 느낄 수 있습니다.', 340, 100, 53, 42, 14, 6, 180);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '초콜릿 크림 칩 프라푸치노', 6000, '모카 소스와 진한 초콜릿 칩, 초콜릿 드리즐이 올라간 달콤한 크림 프라푸치노입니다.', 300, 10, 43, 40, 12, 6, 160);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '모카 프라푸치노', 5700, '초콜릿과 커피가 어우러진 프라푸치노 입니다.', 280, 90, 43, 36, 11, 5, 180);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '카라멜 프라푸치노', 5900, '카라멜과 커피가 어우러진 프라푸치노 입니다.', 300, 85, 46, 39, 11, 5, 190);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '바닐라 크림 프라푸치노', 5100, '신선한 우유와 바닐라 시럽이 어우러진 크림 프라푸치노입니다.', 200, 0, 28, 28, 8, 3, 150);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '민트 초콜릿 칩 블렌디드', 3000, '진한 에스프레소와 뜨거운 물을 섞어 저희 카페의 깔끔하고 강렬한 에스프레소를 가장 부드럽게 잘 느낄 수 있습니다', 10, 150, 2, 0, 0, 1, 5);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '망고 바나나 블렌디드', 6300, '인기 음료인 망고 패션후르츠 블렌디드에 신선한 바나나 1개가 통째로 들어간 달콤한 프라푸치노입니다.', 270, 0, 62, 48, 2, 3, 110);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '레드 파워 스매시 블렌디드', 6500, '붉은 색의 에너지로 가득 채워져 시원하고 상큼하게 즐길 수 있습니다.', 270, 17, 67, 47, 1, 1, 30);
SET DEFINE OFF;
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '피치 & 레몬 블렌디드', 6300, '복숭아와 레몬, 말랑한 복숭아 젤리가 조화로운 과일 블렌디드입니다.', 190, 0, 48, 27, 0, 0, 45);
SET DEFINE ON;
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '딸기 딜라이트 요거트 블렌디드', 6300, '유산균이 살아있는 리얼 요거트와 풍성한 딸기 과육이 더욱 상큼하게 어우러진 과일 요거트 블렌디드입니다.', 370, 0, 63, 57, 8, 7, 110);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '자몽 허니 블랙 티', 5700, '새콤한 자몽과 달콤한 꿀이 깊고 그윽한 풍미의 티바나 블랙티의 조화를 맛 볼 수 있습니다.', 80, 70, 20, 17, 0, 0, 5);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '얼 그레이 티', 4500, '꽃향 가득한 라벤더와 베르가못 향이 진한 홍차와 블렌딩된 향긋한 블랙 티', 0, 70, 0, 0, 0, 0, 0);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '유스베리 티', 4500, '제주산 유기농 찻잎으로 만든 황차에 사과, 망고, 파인애플, 로즈힙 등이 블렌딩되어 핑크빛 컬러가 감도는 수색과 베리류의 새콤함을 느낄 수 있는 옐로우 티', 0, 20, 0, 0, 0, 0, 5);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '민트 블렌드 티', 4500, '스피어민트, 페퍼민트, 레몬머틀이 블렌딩된 상쾌한 허브 티', 0, 0, 2, 0, 0, 0, 5);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 1, '히비스커스 블렌드 티', 4500, '히비스커스, 사과, 파피야, 망고, 레몬그라스 등이 블렌딩된 상큼한 허브 티', 0, 0, 2, 0, 0, 0, 5);                                                            
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '딸기 쏙 생크림 케이크', 6200, '촉촉한 케이크 시트 사이에 신선한 생 딸기와 부드럽고 달콤한 우유 생크림을 겹겹이 쌓아 올린 케이크입니다.', 295, 0, 22, 22, 21, 4, 110);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '피넛 크림 바나나 케이크', 6200, '바나나를 넣은 케이크 시트에 고소한 피넛 크림과 진한 가나슈를 샌드한 후 향긋한 바나나 생크림으로 마무리한 케이크입니다.', 475, 0, 23, 22, 20, 1, 115);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '리치 가나슈 케이크', 6200, '달콤한 초콜릿 케이크 시트에 두유와 다크 초콜릿으로 만든 진한 가나슈 크림을 샌드한 후 고소한 피칸을 올린 케이크입니다.', 320, 0, 27, 25, 24, 1, 110);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '흑임자 크림 케이크', 6200, '촉촉한 흑임자 케이크 시트 사이에 고소하고 달콤한 흑임자 크림이 층층이 샌드되어 있는 케이크입니다.', 385, 0, 26, 25, 22, 1, 112);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '레이어 가나슈 케이크', 5700, '초콜릿, 가나슈, 모카로 만든 시트와 크림이 7개의 층을 이루어 모양부터 매력적인 케이크입니다.', 578, 0, 52, 37, 37, 7, 165);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '이탈리안 바게트 샌드위치', 6300, '모차렐라 치즈, 콜비잭 치즈, 블랙라벨햄, 루꼴라가 들어간 겉은 바삭 속은 쫄깃한 이탈리안 스타일의 정통 바게트 샌드위치입니다.', 435, 0, 42, 7, 24, 13, 999);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '햄 루꼴라 올리브 샌드위치', 6200, '햄, 토마토, 모짜렐라 치즈와 루꼴라를 올리브가 콕콕 박힌 치아바타 사이에 넣은 샌드위치입니다.', 388, 0, 42, 1, 0, 1, 658);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '크랜베리 치킨 치즈 샌드위치', 4500, '건강하고 고소한 곡물 식빵 안에 크랜베리 치킨 스프레드와 토마토, 치즈를 넣어 맛이 더욱 풍부해진 콜드 샌드위치입니다.', 416, 0, 40, 11, 20, 17, 774);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '리코타 치즈 바게트 샌드위치', 6200, '리코타 치즈, 살라미, 토마토, 루꼴라가 들어간 겉은 바삭 속은 쫄깃한 정통 바게트 스타일의 샌드위치입니다.', 385, 0, 40, 12, 19, 14, 800);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '에그에그 샌드위치', 4300, '화이트 식빵 사이에 고소한 에그 스프레드를 넣은 부드러운 샌드위치입니다.', 386, 0, 27, 7, 22, 15, 800);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '바닐라 아포가토', 5700, '유기농 바닐라 아이스크림에 에스프레소를 부어 만든 달콤 쌉싸름한 이탈리아 정통 디저트입니다.', 208, 75, 17, 17, 13, 3, 51);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '초콜릿 아포가토', 5500, '유기농 초콜릿 아이스크림에 에스프레소를 부어 만든 달콤 쌉싸름한 이탈리아 정통 디저트입니다.', 231, 75, 27, 22, 12, 4, 42);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '자바칩 아포가토', 6600, '유기농 초콜릿 아이스크림에 에스프레소를 부어 만든 달콤 쌉싸름한 이탈리아 정통 디저트입니다.', 230, 75, 33, 22, 12, 5, 33);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '유기농 아이스크림 바닐라', 2900, '부드럽고 깔끔한 맛의 유기농 아이스크림을 즐기세요.', 203, 0, 18, 32, 21, 122, 51);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '유기농 아이스크림 초콜릿', 10000, '부드럽고 깔끔한 맛의 유기농 아이스크림을 즐기세요.', 226, 0, 26, 34, 21, 11, 52);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '클래식 스콘', 3300, '프랑스산 고급 버터로 만든 담백한 스콘입니다.', 468, 0, 41, 17, 20, 21, 130);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '미니 클래식 스콘', 4000, '미니 사이즈의 담백한 스콘입니다.', 456, 0, 51, 12, 11, 10, 500);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '트리플 미니 스콘', 4300, '헤이즐넛 초콜릿, 치즈, 크랜베리 세 가지 맛의 스콘이 각각 들어있는 미니 사이즈의 스콘입니다.', 468, 0, 58, 15, 8, 22, 556);
INSERT INTO MENU VALUES(MN_IDX_SEQ.NEXTVAL, 2, '콰트로 치즈 스콘', 4600, '모짜렐라, 체더, 프로볼로네, 그라나 파다노 네 가지 치즈를 넣어 진한 치즈 풍미가 특징인 스콘입니다.', 399, 0, 36, 20, 20, 12, 528);
INSERT INTO MENU(MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '워드마크 스퀘어 머그 273ml', 12000, '그린 컬러에 2SOME 워드마크가 매치된 273ml 머그입니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '크림 사이렌 스퀘어 머그 355ml', 10000, '크림 컬러에 사이렌 로고가 매치된 355ml 머그입니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '그린 사이렌 스퀘어 머그 473ml', 16000, '다크 그린 컬러에 사이렌 로고가 매치된 473ml 머그입니다.');
-- 액세서리 - 사이렌 리사이클드 쇼퍼백 / 스트로 세트
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '그린 팝핸들 콜드컵 473ml', 19000, '손가락 사이에 끼는 핸들이 부착된 찬 음료 전용 플라스틱 골드컵입니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '그린 워드마크 폼 콜드컵 473ml', 18000, '워드마크가 돋보이는 473ml 용량의 찬 음료 전용 플라스틱 텀블러입니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '그린 그러데이션 사이렌 콜드컵 591ml', 212000, '그린 컬러의 리드와 빨대가 매치된 찬 음료 전용의 플라스틱 콜드컵입니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '사이렌 리사이클드 쇼퍼백', 10000, '폴더블 쇼퍼백으로 파우치 형태로 압축 가능합니다.');
INSERT INTO MENU (MN_IDX, CATEGORY_IDX, MN_NAME, PRICE, DETAIL)
VALUES(MN_IDX_SEQ.NEXTVAL, 3, '스트로 세트', 10000, '실리콘 스트로 3종과 세척솔, 린넨 파우치로 구성된 세트 상품입니다.');
--------------------

--메뉴/추가옵션 매핑 테이블 삽입---
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, 'HOT/ICE', 'HOT', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, 'HOT/ICE', 'ICE', NULL);
-- 여기 가격 수정하고
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, 'SIZE', 'TALL', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL,'SIZE', 'GRANDE', 500);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, 'SIZE', 'VENTI', 1000);
-- 여기까지
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '컵 선택', '매장컵', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '컵 선택', '개인컵', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '컵 선택', '일회용컵', NULL);
-- 여기부터 샷추가 안함, 가격 수정했어용
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '샷 추가', '안함', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '샷 추가', '1샷', 500);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '샷 추가', '2샷', 1000);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '샷 추가', '3샷', 1500);
--여기까지
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '얼음 양', '적게', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '얼음 양', '보통', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '얼음 양', '많이', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '휘핑크림', '없이', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '휘핑크림', '적게', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '휘핑크림', '보통', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '휘핑크림', '많이', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '워밍', '따뜻하게 데움', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '워밍', '데우지 않음', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '재료 추가', '버터 추가', 500);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '재료 추가', '잼 추가', 700);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '재료 추가', '크림치즈 추가', 1000);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '포장', '선물 포장', NULL);
INSERT INTO ADDITIONAL_OPTION
VALUES (ADI_OPT_IDX_SEQ.NEXTVAL, '포장', '비포장', NULL);
---------------------------------------

INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (1, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (2, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (3, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (4, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (5, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (6, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, 'HOT/ICE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, 'SIZE');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, '얼음 양');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, '컵 선택');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (7, '휘핑크림');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (26, '워밍');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (27, '워밍');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (28, '워밍');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (29, '워밍');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (30, '워밍');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (31, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (32, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (33, '샷 추가');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (40, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (41, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (42, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (43, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (44, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (45, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (46, '포장');
INSERT INTO MENU_ADDITIONAL_OPTION_MAP VALUES (47, '포장');

-------------------------------------------

--COUPON INSERT (쿠폰)
INSERT INTO COUPON (NO,VARNO,LIMITDATE)VALUES (1,1,'2022/01/01');
INSERT INTO COUPON (NO,VARNO,LIMITDATE)VALUES (2,1,'2022/01/01');

--COU_VAR INSERT (쿠폰 종류)
INSERT INTO COU_VAR (VARNO,NAME,VALUE,INTRODU) VALUES (1,'쿠폰',1000,'쿠폰쿠폰');

--CUSTOMER INSERT (고객)
INSERT INTO CUSTOMER (NO,NAME, PHONE, BIRTH, STAMP) VALUES (CUSTOMER_NO_SEQ.NEXTVAL,'고객','010-1111-1111','2022/01/01',20);
INSERT INTO CUSTOMER (NO,NAME, PHONE, BIRTH) VALUES (CUSTOMER_NO_SEQ.NEXTVAL,'고객','010-2222-2222','2022/01/01');

-----------------------------
--주문 테이블

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/04/23', 6000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 2, 6000, 1);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/04/25', 3000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 1, 3000, 2);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/20', 3000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 1, 3000, 3);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/20', 10300);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 2, 1, 4000, 4);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 14, 1, 6300, 4);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/21', 12300);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 6, 1, 6300, 5); 
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 2, 6000, 5);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/22', 16000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 40, 1, 12000, 6);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 2, 1, 4000, 6);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/23', 13700);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 3, 2, 8000, 7);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 16, 1, 5700, 7);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/23', 12200);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 21, 1, 6200, 8);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 2, 6000, 8);

--5월24일 아메리카노 1000원 할인
INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE, USE_POINT, DISCOUNT_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/24', 7700, 0, 1000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 1, 3000, 9);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 16, 1, 5700, 9);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE, USE_POINT, DISCOUNT_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/24', 6500, 0, 1000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 1, 3000, 10);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 17, 1, 4500, 10);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE, USE_POINT, DISCOUNT_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/24', 10500, 0, 3000);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 3, 9000, 11);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 17, 1, 4500, 11);
--

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE, USE_POINT, DISCOUNT_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/25', 17400, 0, 0);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 1, 2, 6000, 12);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 3, 1, 5700, 12);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 16, 1, 5700, 12);

INSERT INTO ORDER_GROUP(GROUP_NO, ORDER_DATE, TOTAL_PRICE, USE_POINT, DISCOUNT_PRICE) VALUES (GROUP_SEQ.NEXTVAL, '22/05/25', 18900, 0, 0);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 12, 2, 12600, 13);
INSERT INTO ORDER_ITEM(NO, MN_IDX, ITEM_NUM, ITEM_PRICE, GROUP_NO) VALUES (ITEM_SEQ.NEXTVAL, 15, 1, 6300, 13);


COMMIT;
