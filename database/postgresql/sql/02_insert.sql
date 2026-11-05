----------------------------------------------------------------------------------------
-- member 테이블 샘플 INSERT 예시
----------------------------------------------------------------------------------------
-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member01', '홍길동', 'password123', 'member01@example.com');

-- 컬럼 리스트 생략 (테이블 컬럼 순서와 개수에 맞춰야 함)
-- INSERT INTO member
-- VALUES ('member02', '김철수', 'password456', 'member02@example.com', DEFAULT);
-- COMMIT;



-- 기본값을 갖는 컬럼 생략
-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member03', '이영희', 'password789', 'member03@example.com');
-- COMMIT;

-- NULL을 가질 수 있는 컬럼 생략
-- INSERT INTO member (member_id, member_name, password)
-- VALUES ('member04', '박민수', 'password321');
-- COMMIT;

-- INSERT INTO member (member_id, member_name, password, email)
-- VALUES ('member05', '최지훈', 'password654', NULL);
-- COMMIT;

----------------------------------------------------------------------------------------
-- seller 테이블 샘플 INSERT 예시
----------------------------------------------------------------------------------------
-- INSERT INTO seller (seller_name, email, phone)
-- VALUES ('홍길동', 'hong1@company.com', '01012341234');
-- INSERT INTO seller (seller_name, email, phone)
-- VALUES ('홍길남', 'hong2@company.com', '01012341235');
-- COMMIT;

----------------------------------------------------------------------------------------
-- 모든 테이블의 기존 데이터 삭제 후 1,000건씩 생성
----------------------------------------------------------------------------------------
BEGIN;
DELETE FROM order_item;
DELETE FROM review;
DELETE FROM orders;
DELETE FROM product;
DELETE FROM seller;
DELETE FROM member;

INSERT INTO member (member_id, member_name, password, email)
SELECT 'user' || i, 'member' || i, 'abc12345', 'user' || i || '@example.com'
FROM generate_series(1, 1000) AS g(i);

INSERT INTO seller (seller_id, seller_name, email, phone)
SELECT i, 'seller' || i, 'seller' || i || '@example.com',
       '010' || LPAD((10000000 + i)::text, 8, '0')
FROM generate_series(1, 1000) AS g(i);

INSERT INTO product (product_id, product_name, price, seller_id)
SELECT i, 'product' || i, 1000 + i, i
FROM generate_series(1, 1000) AS g(i);

INSERT INTO orders (order_id, member_id)
SELECT i, 'user' || i
FROM generate_series(1, 1000) AS g(i);

INSERT INTO order_item (order_id, product_id, quantity, unit_price)
SELECT i, i, (i % 5) + 1, 1000 + i
FROM generate_series(1, 1000) AS g(i);

INSERT INTO review (review_id, title, content, writer, written_at, product_id)
SELECT i, 'product' || i || ' 후기', 'product' || i || ' 샘플 상품에 대한 후기입니다.',
       'user' || i, CURRENT_TIMESTAMP, i
FROM generate_series(1, 1000) AS g(i);

-- 명시적으로 넣은 ID 다음 값부터 IDENTITY가 생성되도록 조정
ALTER TABLE seller ALTER COLUMN seller_id RESTART WITH 1001;
ALTER TABLE product ALTER COLUMN product_id RESTART WITH 1001;
ALTER TABLE orders ALTER COLUMN order_id RESTART WITH 1001;
ALTER TABLE review ALTER COLUMN review_id RESTART WITH 1001;
COMMIT;
