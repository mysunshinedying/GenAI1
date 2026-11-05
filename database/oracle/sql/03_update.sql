UPDATE member SET
    member_name = '홍길동',
    password = 'abc54321'
WHERE
    -- member_id = 'user1';
    member_id LIKE '%user10%' AND email = 'user1000@example.com';

----------------------------------------------------------------------

UPDATE product SET
    price = price + 10
WHERE
    price <= 1500;

----------------------------------------------------------------------
-- NULL 값을 수정하거나 조건으로 사용
UPDATE member SET
    email = NULL
WHERE member_id = 'user1';

UPDATE member SET
    email = 'abc@mycompany.com'
WHERE email IS NULL;

----------------------------------------------------------------------
-- 날짜를 조건으로 해서 날짜를 수정
UPDATE member SET
    joined_at = '2026/01/05'
WHERE joined_at < '2026/01/05';

----------------------------------------------------------------------
-- review 테이블에 첨부 파일 정보 컬럼 추가 (이미 있으면 건너뜀)
DECLARE
    v_column_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_column_count
    FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_NAME';
    IF v_column_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_name VARCHAR2(255))';
    END IF;

    SELECT COUNT(*) INTO v_column_count
    FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_TYPE';
    IF v_column_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_type VARCHAR2(100))';
    END IF;

    SELECT COUNT(*) INTO v_column_count
    FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_DATA';
    IF v_column_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_data BLOB)';
    END IF;
END;
/

----------------------------------------------------------------------
-- 게시글 제목과 내용을 review_id에 따라 다양하게 수정
UPDATE review r
SET (title, content) = (
    SELECT
        CASE MOD(r.review_id, 10)
            WHEN 0 THEN p.product_name || ' 사용 후기: 기대 이상이에요'
            WHEN 1 THEN p.product_name || ' 후기 및 구매 팁'
            WHEN 2 THEN '직접 써본 ' || p.product_name || ' 리뷰'
            WHEN 3 THEN p.product_name || ' 사용 첫인상'
            WHEN 4 THEN p.product_name || ' 장단점 정리'
            WHEN 5 THEN p.product_name || ' 재구매 의사 있습니다'
            WHEN 6 THEN p.product_name || ' 배송부터 사용까지 후기'
            WHEN 7 THEN p.product_name || ' 궁금한 점과 답변'
            WHEN 8 THEN p.product_name || ' 한 달 사용기'
            ELSE p.product_name || ' 추천합니다'
        END,
        CASE MOD(r.review_id, 10)
            WHEN 0 THEN '포장을 열었을 때부터 마감이 깔끔해서 만족스러웠습니다. ' || p.product_name || '을(를) 며칠 사용해 보니 설명에 나온 기능도 잘 작동하네요.'
            WHEN 1 THEN '주문 후 배송이 빨랐고 제품 상태도 좋았습니다. ' || p.product_name || '은(는) 사용법이 어렵지 않아 바로 익숙해졌어요.'
            WHEN 2 THEN '기대했던 것보다 실용적이라 자주 손이 갑니다. 특히 마감과 사용감이 마음에 들었습니다. ' || p.product_name || ' 후기입니다.'
            WHEN 3 THEN '처음 받아 본 날 바로 사용해 봤습니다. 크기와 구성은 안내된 내용과 같았고, 기본 성능도 만족스럽습니다.'
            WHEN 4 THEN '장점은 간편한 사용법과 안정적인 품질입니다. 디자인은 취향을 탈 수 있지만 전체적으로 만족스럽습니다.'
            WHEN 5 THEN '며칠 써보고 마음에 들어 후기를 남깁니다. 실제 사용감이 좋아 다음에도 다시 구매할 생각입니다.'
            WHEN 6 THEN '배송 상자와 제품 모두 손상 없이 도착했습니다. 구성품도 빠짐없이 들어 있었고 작동도 문제없네요.'
            WHEN 7 THEN '사용 전에는 관리가 번거로울까 걱정했는데 생각보다 간단합니다. 지금까지는 만족 중이에요.'
            WHEN 8 THEN '한동안 사용하면서 느낀 점은 기본에 충실하다는 것입니다. 매일 편하게 쓸 수 있는 점이 좋았어요.'
            ELSE '비슷한 제품을 비교한 뒤 선택했습니다. 마감이 괜찮고 사용도 편리해 구매에 만족합니다.'
        END
    FROM product p
    WHERE p.product_id = r.product_id
);
