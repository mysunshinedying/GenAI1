--------------------------------------------------------
-- 여러 테이블을 연결해서 데이터 가져오기(JOIN)
-- 조건: 두 테이블이 참조 연관관계여야함
SELECT m.member_name, b.title
FROM member m, review b
WHERE m.member_id = b.writer
AND m.member_id = 'user1';

SELECT m.member_id, m.member_name, o.order_id, o.ordered_at
FROM member m, orders o
WHERE m.member_id = o.member_id
AND m.member_id = 'user1';

------------------------------------------------------
SELECT b.title, p.product_name, s.seller_name, m.member_name
FROM product p, review b, seller s, member m
WHERE p.product_id = b.product_id
AND p.seller_id = s.seller_id
AND b.writer = m.member_id
AND p.product_id = 1;
