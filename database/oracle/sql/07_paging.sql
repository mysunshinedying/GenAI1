-- review 테이블 페이징 조회
-- rows = 10, pageNum = 1
-- 시작 행 번호: (pageNum - 1) * rows = 0
SELECT review_id, title, content, writer, written_at, product_id
FROM review
ORDER BY review_id DESC
OFFSET (1 - 1) * 10 ROWS
FETCH NEXT 10 ROWS ONLY;

-- ROWNUMBER 버전

SELECT *
FROM (
    SELECT review_id, title, content, writer, written_at, product_id,
           ROW_NUMBER() OVER (ORDER BY review_id DESC) AS rn
    FROM review
)
WHERE rn BETWEEN 1 AND 10;
