-------------- 주문관련 삭제 -------------------
DROP TABLE ORDER_ITEM_ADD;  -- 주문아이템_추가옵션 삭제
DROP TABLE ORDER_ITEM;  --주문아이템 테이블 삭제
DROP TABLE ORDER_GROUP; --주문 테이블 삭제

DROP SEQUENCE GROUP_SEQ;    --그룹시퀀스 삭제
DROP SEQUENCE ITEM_SEQ; --아이템시퀀스 삭제

--------------- 메뉴관련 삭제 -------------------

DROP TABLE CATEGORY;    -- 카테고리 테이블 삭제
DROP TABLE MENU;    -- 메뉴 테이블 삭제
DROP TABLE ADDITIONAL_OPTION;   -- 추가옵션 정보 테이블 삭제
DROP TABLE MENU_ADDITIONAL_OPTION_MAP;  --매핑 테이블 삭제

DROP SEQUENCE CATEGORY_SEQ; -- 카테고리 시퀀스 삭제
DROP SEQUENCE MENU_SEQ; -- 메뉴 시퀀스 삭제
DROP SEQUENCE OPTION_SEQ;   -- 추가옵션 시퀀스 삭제

