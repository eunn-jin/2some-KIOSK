----------- 전체 테이블 생성 ------------

-- 카테고리 테이블
------------------------------------------------------------------------
CREATE TABLE CATEGORY(
      CATEGORY_IDX  NUMBER
    , CATEGORY_NAME  VARCHAR2(100)
    , CONSTRAINT CATEGORY_PK PRIMARY KEY(CATEGORY_IDX)
);    
------------------------------------------------------------------------

-- 메뉴 테이블
------------------------------------------------------------------------
CREATE TABLE MENU(
      MN_IDX        NUMBER               PRIMARY KEY
    , CATEGORY_IDX  NUMBER               NOT NULL
    , MN_NAME       VARCHAR(100)         NOT NULL
    , PRICE         NUMBER               NOT NULL
    , DETAIL        VARCHAR(1000)        NOT NULL
    , CAL           NUMBER
    , CAFFEINE      NUMBER
    , CARBOHYDRATE  NUMBER
    , SUGAR         NUMBER
    , FAT           NUMBER
    , PROTEIN       NUMBER
    , NATRIUM       NUMBER
);
--------------------------------------------------------------------------
-- 메뉴테이블 시퀀스
CREATE SEQUENCE MN_IDX_SEQ NOCACHE NOCYCLE;

-- 추가옵션 정보 테이블 
------------------------------------------------------------------------
CREATE TABLE ADDITIONAL_OPTION(
      ADI_OPT_IDX     NUMBER          NOT NULL        
    , ADI_OPT_NAME    VARCHAR2(100)   NOT NULL
    , ADI_OPT_CHOICE  VARCHAR2(100)   NOT NULL
    , ADI_OPT_PRICE   NUMBER
);
------------------------------------------------------------------------
-- 추가옵션인덱스_시퀀스
CREATE SEQUENCE ADI_OPT_IDX_SEQ NOCACHE NOCYCLE;

-- 메뉴/추가옵션 매핑 테이블
------------------------------------------------------------------------
CREATE TABLE MENU_ADDITIONAL_OPTION_MAP (
      MN_IDX          NUMBER          NOT NULL 
    , ADI_OPT_NAME    VARCHAR2(100)   NOT NULL
);
------------------------------------------------------------------------

-- 주문 테이블
CREATE TABLE ORDER_GROUP (
      GROUP_NO NUMBER PRIMARY KEY
    , ORDER_DATE DATE DEFAULT SYSDATE NOT NULL
    , U_NUM NUMBER
    , TOTAL_PRICE NUMBER NOT NULL
    , USE_POINT NUMBER DEFAULT 0 NOT NULL
    , DISCOUNT_PRICE NUMBER DEFAULT 0 NOT NULL
);
--ALTER TABLE ORDER_GROUP ADD CONSTRAINT CUSTOMER_FK FOREIGN KEY(U_NUM) REFERENCES CUSTOMER(NUM);

-- 주문아이템 테이블
CREATE TABLE ORDER_ITEM (
      NO NUMBER PRIMARY KEY
    , MN_IDX NUMBER NOT NULL
    , ITEM_PRICE NUMBER NOT NULL
    , ITEM_NUM NUMBER DEFAULT 1 NOT NULL
    , GROUP_NO NUMBER NOT NULL
    , CONSTRAINT GROUP_FK FOREIGN KEY(GROUP_NO) REFERENCES ORDER_GROUP(GROUP_NO)
);
--ALTER TABLE ORDER_ITEM ADD CONSTRAINT MN_FK FOREIGN KEY(MN_IDX) REFERENCES MENU(MN_IDX);


-- 주문아이템_추가옵션
CREATE TABLE ORDER_ITEM_ADD (
      ITEM_NO NUMBER NOT NULL
    , ADI_OPT_IDX NUMBER NOT NULL
    , CONSTRAINT ITEM_FK FOREIGN KEY(ITEM_NO) REFERENCES ORDER_ITEM(NO) ON DELETE CASCADE
);
--ALTER TABLE ORDER_ITEM_ADD ADD CONSTRAINT ADD_FK FOREIGN KEY(ADI_OPT_IDX) REFERENCES ADDITIONAL_OPTION(ADI_OPT_IDX);

--시퀀스 생성
CREATE SEQUENCE GROUP_SEQ NOCYCLE NOCACHE;
CREATE SEQUENCE ITEM_SEQ NOCYCLE NOCACHE;

-- 고객 테이블 생성
CREATE TABLE CUSTOMER (
    NO NUMBER PRIMARY KEY
    , NAME VARCHAR2(12) NOT NULL
    , PHONE CHAR(13) NOT NULL UNIQUE
    , BIRTH CHAR(10) NOT NULL
    , STAMP NUMBER DEFAULT 0
    , ENROLL TIMESTAMP DEFAULT SYSTIMESTAMP
    , QUIT CHAR(1) DEFAULT 'N' CHECK(QUIT IN ('Y','N'))
);

-- 쿠폰 테이블 생성
CREATE TABLE COUPON (
    NO NUMBER PRIMARY KEY
    ,VARNO NUMBER NOT NULL
    ,GETDATE TIMESTAMP DEFAULT SYSTIMESTAMP
    ,USEDATE TIMESTAMP
    ,USE_CHECK CHAR(1) DEFAULT 'N' CHECK(USE_CHECK IN ('Y','N'))
    ,LIMITDATE CHAR(10) NOT NULL 
    
);

-- 쿠폰 종류 테이블 생성
CREATE TABLE COU_VAR (
    VARNO NUMBER PRIMARY KEY
    , NAME VARCHAR2(100) NOT NULL
    , VALUE NUMBER NOT NULL
    , INTRODU VARCHAR2(200) NOT NULL
)
;

CREATE SEQUENCE CUSTOMER_NO_SEQ
INCREMENT BY 1 START WITH 1
MINVALUE 1
MAXVALUE 999
NOCYCLE
NOCACHE
;

--매니저 테이블 생성
CREATE TABLE MANAGER(
    NO NUMBER PRIMARY KEY
    , ID VARCHAR2(30) NOT NULL UNIQUE
    , PWD VARCHAR2(30) NOT NULL
    , NAME VARCHAR2(30) NOT NULL UNIQUE
    , PHONE CHAR(13) NOT NULL UNIQUE
    , EMAIL VARCHAR2(30) NOT NULL UNIQUE
    , ENROLL_DATE TIMESTAMP DEFAULT SYSDATE
    , QUIT_YN CHAR(1) DEFAULT 'N' CHECK(QUIT_YN IN ('Y','N'))
    , CONNECTION_YN CHAR(1) DEFAULT 'N' CHECK(CONNECTION_YN IN ('Y','N'))
);

-- 매니저 시퀀스 생성
CREATE SEQUENCE MANAGER_NO_SEQ
INCREMENT BY 1 -- 증감숫자
 START WITH 1 -- 시작값
MINVALUE 1 -- 최소값
MAXVALUE 999 -- 최대값
NOCYCLE -- CYCLE --순환여부
NOCACHE -- 캐시 여부 (사이즈)
;