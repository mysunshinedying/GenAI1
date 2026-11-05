-- 기존 board 데이터를 보존하면서 review 테이블로 전환
BEGIN;

DO $$
BEGIN
    IF to_regclass('public.board') IS NOT NULL
       AND to_regclass('public.review') IS NULL THEN
        ALTER TABLE public.board RENAME TO review;
    END IF;

    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'board_id'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'review_id'
    ) THEN
        ALTER TABLE public.review RENAME COLUMN board_id TO review_id;
    END IF;
    IF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'writed_at'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'written_at'
    ) THEN
        ALTER TABLE public.review RENAME COLUMN writed_at TO written_at;
    ELSIF EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'date'
    ) AND NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_schema = 'public' AND table_name = 'review' AND column_name = 'written_at'
    ) THEN
        ALTER TABLE public.review RENAME COLUMN date TO written_at;
    END IF;

    IF EXISTS (SELECT 1 FROM pg_constraint WHERE conrelid = 'public.review'::regclass AND conname = 'fk_board_member')
       AND NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conrelid = 'public.review'::regclass AND conname = 'fk_review_member') THEN
        ALTER TABLE public.review RENAME CONSTRAINT fk_board_member TO fk_review_member;
    END IF;
    IF EXISTS (SELECT 1 FROM pg_constraint WHERE conrelid = 'public.review'::regclass AND conname = 'fk_board_product')
       AND NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conrelid = 'public.review'::regclass AND conname = 'fk_review_product') THEN
        ALTER TABLE public.review RENAME CONSTRAINT fk_board_product TO fk_review_product;
    END IF;
END $$;

ALTER TABLE review
    ADD COLUMN IF NOT EXISTS file_name VARCHAR(255),
    ADD COLUMN IF NOT EXISTS file_type VARCHAR(100),
    ADD COLUMN IF NOT EXISTS file_data BYTEA;

UPDATE review b
SET title = CASE (b.review_id % 10)
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
    content = CASE (b.review_id % 10)
        WHEN 0 THEN '포장을 열었을 때부터 마감이 깔끔해서 만족스러웠습니다. ' || p.product_name || '을(를) 며칠 사용해 보니 설명에 나온 기능도 잘 작동하네요. 가격과 품질을 함께 생각하면 좋은 선택이라고 느꼈습니다.'
        WHEN 1 THEN '주문 후 배송이 빨랐고 제품 상태도 좋았습니다. ' || p.product_name || '은(는) 사용법이 어렵지 않아 바로 익숙해졌어요. 구매를 고민하는 분께 참고가 되길 바랍니다.'
        WHEN 2 THEN '기대했던 것보다 실용적이라 자주 손이 갑니다. 특히 마감과 사용감이 마음에 들었습니다. ' || p.product_name || '을(를) 실제로 써본 경험을 바탕으로 남기는 후기입니다.'
        WHEN 3 THEN '처음 받아 본 날 바로 사용해 봤습니다. 크기와 구성은 안내된 내용과 같았고, 기본적인 성능도 만족스럽습니다. ' || p.product_name || '을(를) 찾고 있다면 비교 목록에 넣어볼 만해요.'
        WHEN 4 THEN '장점은 간편한 사용법과 안정적인 품질입니다. 아쉬운 점을 꼽자면 개인 취향에 따라 디자인이 평범하게 느껴질 수 있겠네요. 그래도 ' || p.product_name || '은(는) 전체적으로 만족스러웠습니다.'
        WHEN 5 THEN '며칠 써보고 마음에 들어 후기를 남깁니다. 처음에는 가격을 고민했지만 실제 사용감이 좋아 납득이 됐어요. ' || p.product_name || '은(는) 다음에도 다시 구매할 생각입니다.'
        WHEN 6 THEN '배송 상자와 제품 모두 손상 없이 도착했습니다. 안내된 구성품도 빠짐없이 들어 있었고 작동도 문제없네요. ' || p.product_name || '을(를) 주문할 분들은 참고하세요.'
        WHEN 7 THEN '사용 전에는 관리가 번거로울까 걱정했는데 생각보다 간단합니다. 며칠 더 써봐야 알겠지만 지금까지는 만족 중이에요. ' || p.product_name || '의 내구성도 오래 좋았으면 합니다.'
        WHEN 8 THEN '한동안 사용하면서 느낀 점은 기본에 충실하다는 것입니다. 과하게 튀는 기능보다 매일 편하게 쓸 수 있는 점이 좋았어요. ' || p.product_name || '에 관심 있는 분께 도움이 되었으면 합니다.'
        ELSE '비슷한 제품을 몇 가지 비교한 뒤 선택했습니다. 실제로 받아 보니 마감이 괜찮고 사용도 편리하네요. ' || p.product_name || '을(를) 구매한 선택에 만족합니다.'
    END
FROM product p
WHERE b.product_id = p.product_id;

COMMIT;
