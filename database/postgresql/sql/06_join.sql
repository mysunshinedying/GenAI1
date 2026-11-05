-- 회원과 게시물 조인
SELECT m.member_name, b.title
FROM member m
JOIN review b ON m.member_id = b.writer
WHERE m.member_id = 'user1';

------------------------------------------------------
-- 회원과 주문 조인
SELECT m.member_id, m.member_name, o.order_id, o.ordered_at
FROM member m
JOIN orders o ON m.member_id = o.member_id
WHERE m.member_id = 'user1';

------------------------------------------------------
-- 상품, 게시물, 판매자, 회원 조인
SELECT b.title, p.product_name, s.seller_name, m.member_name
FROM product p
JOIN review b ON p.product_id = b.product_id
JOIN seller s ON p.seller_id = s.seller_id
JOIN member m ON b.writer = m.member_id
WHERE p.product_id = 1;
