-------------------------------------------------------
-- 모든 컬럼의 값을 가져오기

SELECT *
FROM review
WHERE review_id <= 10;

-------------------------------------------------------
-- 특정 컬럼의 값을 가져오기
SELECT review_id, title, writer
FROM review
WHERE review_id <= 5;

-------------------------------------------------------
-- 정렬해서 가져오기
SELECT review_id, title, writer
FROM review
WHERE review_id <= 5
ORDER BY review_id DESC;


-------------------------------------------------------
-- 1차 정렬, 2차 정렬해서 가져오기
SELECT *
FROM order_item
WHERE order_id <= 3
ORDER BY order_id ASC, product_id DESC;

-------------------------------------------------------
-- 행의 개수 얻기
SELECT COUNT(*)
FROM order_item;

-------------------------------------------------------
-- 그룹핑하기
SELECT order_id, COUNT(product_id)
FROM order_item
GROUP BY order_id
ORDER BY COUNT(product_id) DESC;

-------------------------------------------------------
SELECT order_id, product_id, COUNT(*)
FROM order_item
WHERE order_id <= 3
GROUP BY order_id, product_id;

SELECT order_id, SUM(unit_price * quantity) AS amount
FROM order_item
GROUP BY order_id
ORDER BY amount DESC;

--------------------------------------------------------
-- 집계 결과에 조건 달기
SELECT order_id, SUM(unit_price * quantity) AS amount
FROM order_item
GROUP BY order_id
HAVING SUM(unit_price * quantity) >= 10000;


