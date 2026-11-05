-- review 테이블 페이징 조회
-- rows = 10, pageNum = 1
-- OFFSET = (pageNum - 1) * rows = 0
SELECT review_id, title, content, writer, date, product_id
FROM review
ORDER BY review_id DESC
LIMIT 10 OFFSET 0;

-- ROW_NUMBER()를 이용한 페이징
SELECT review_id, title, content, writer, date, product_id
FROM (
    SELECT review_id, title, content, writer, date, product_id,
           ROW_NUMBER() OVER (ORDER BY review_id DESC) AS rn
    FROM review
) AS numbered_review
WHERE rn BETWEEN 1 AND 10
ORDER BY rn;
