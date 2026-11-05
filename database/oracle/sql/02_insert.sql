----------------------------------------------------------------------------------------
-- member 테이블에 데이터 저장
----------------------------------------------------------------------------------------
-- member 테이블에 회원 한 명 삽입
-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member01', '홍길동', 'password123', 'member01@example.com');

-- -- 컬럼 리스트 생략
-- INSERT INTO member VALUES ('member02', '김철수', 'password456', 'member02@example.com', DEFAULT);
-- COMMIT;

-- -- 기본값을 갖는 컬럼의 값을 생략하고 삽입
-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member03', '이영희', 'password789', 'member03@example.com');
-- COMMIT;

-- -- null을 가질 수 있는 컬럼은 생략 가능
-- INSERT INTO member (member_id, member_name, password)
-- VALUES ('member04', '박민수', 'password321');
-- COMMIT;

-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member05', '최지훈', 'password654', NULL);
-- COMMIT;

----------------------------------------------------------------------------------------
-- SELLER 테이블에 데이터 저장
----------------------------------------------------------------------------------------
-- INSERT INTO seller (seller_name, email, phone)
-- VALUES ('홍길동', 'hong1@company.com', '01012341234');

-- INSERT INTO seller (seller_name, email, phone)
-- VALUES ('홍길남', 'hong2@company.com', '01012341235');
-- COMMIT;

----------------------------------------------------------------------------------------
-- 전체 테이블에 샘플 데이터를 생성
----------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
-- 전체 테이블 샘플 데이터 1,000건씩 생성
-- seller_id와 product_id는 스키마에 따라 숫자 IDENTITY로 자동 생성합니다.
----------------------------------------------------------------------------------------
-- 기존 데이터 삭제 (외래 키가 있는 자식 테이블부터 삭제)
DELETE FROM order_item;
DELETE FROM review;
DELETE FROM orders;
DELETE FROM product;
DELETE FROM seller;
DELETE FROM member;

DECLARE
    v_seller_id seller.seller_id%TYPE;
    v_product_id product.product_id%TYPE;
    v_order_id orders.order_id%TYPE;
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO member (member_id, member_name, password, email)
        VALUES ('user' || i, 'member' || i, 'abc12345', 'user' || i || '@example.com');

        INSERT INTO seller (seller_name, email, phone)
        VALUES ('seller' || i, 'seller' || i || '@example.com', '010' || TO_CHAR(10000000 + i, 'FM00000000'))
        RETURNING seller_id INTO v_seller_id;

        INSERT INTO product (product_name, price, seller_id)
        VALUES ('product' || i, 1000 + i, v_seller_id)
        RETURNING product_id INTO v_product_id;

        INSERT INTO orders (member_id)
        VALUES ('user' || i)
        RETURNING order_id INTO v_order_id;

        INSERT INTO order_item (order_id, product_id, quantity, unit_price)
        VALUES (v_order_id, v_product_id, MOD(i, 5) + 1, 1000 + i);

        INSERT INTO review (title, content, writer, written_at, product_id)
        VALUES ('product' || i || ' 후기', 'product' || i || ' 샘플 상품에 대한 후기입니다.', 'user' || i, SYSDATE, v_product_id);
    END LOOP;

    COMMIT;
END;
/


