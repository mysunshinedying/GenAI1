-- 기존 board 데이터를 보존하면서 review 테이블로 전환
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = 'REVIEW';
    IF v_count = 0 THEN
        SELECT COUNT(*) INTO v_count FROM user_tables WHERE table_name = 'BOARD';
        IF v_count > 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE board RENAME TO review';
        END IF;
    END IF;

    SELECT COUNT(*) INTO v_count FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'BOARD_ID';
    IF v_count > 0 THEN
        SELECT COUNT(*) INTO v_count FROM user_tab_columns
        WHERE table_name = 'REVIEW' AND column_name = 'REVIEW_ID';
        IF v_count = 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE review RENAME COLUMN board_id TO review_id';
        END IF;
    END IF;
    SELECT COUNT(*) INTO v_count FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'WRITED_AT';
    IF v_count > 0 THEN
        SELECT COUNT(*) INTO v_count FROM user_tab_columns
        WHERE table_name = 'REVIEW' AND column_name = 'WRITTEN_AT';
        IF v_count = 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE review RENAME COLUMN writed_at TO written_at';
        END IF;
    ELSE
        SELECT COUNT(*) INTO v_count FROM user_tab_columns
        WHERE table_name = 'REVIEW' AND column_name = 'DATE';
        IF v_count > 0 THEN
            SELECT COUNT(*) INTO v_count FROM user_tab_columns
            WHERE table_name = 'REVIEW' AND column_name = 'WRITTEN_AT';
            IF v_count = 0 THEN
                EXECUTE IMMEDIATE 'ALTER TABLE review RENAME COLUMN date TO written_at';
            END IF;
        END IF;
    END IF;

    SELECT COUNT(*) INTO v_count FROM user_constraints
    WHERE table_name = 'REVIEW' AND constraint_name = 'FK_BOARD_MEMBER';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review RENAME CONSTRAINT fk_board_member TO fk_review_member';
    END IF;
    SELECT COUNT(*) INTO v_count FROM user_constraints
    WHERE table_name = 'REVIEW' AND constraint_name = 'FK_BOARD_PRODUCT';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review RENAME CONSTRAINT fk_board_product TO fk_review_product';
    END IF;
    SELECT COUNT(*) INTO v_count FROM user_constraints
    WHERE table_name = 'REVIEW' AND constraint_name = 'PK_BOARD';
    IF v_count > 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review RENAME CONSTRAINT pk_board TO pk_review';
    END IF;

    SELECT COUNT(*) INTO v_count FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_NAME';
    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_name VARCHAR2(255))';
    END IF;
    SELECT COUNT(*) INTO v_count FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_TYPE';
    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_type VARCHAR2(100))';
    END IF;
    SELECT COUNT(*) INTO v_count FROM user_tab_columns
    WHERE table_name = 'REVIEW' AND column_name = 'FILE_DATA';
    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE review ADD (file_data BLOB)';
    END IF;
END;
/

-- 제목과 내용 갱신은 03_update.sql의 review UPDATE 문을 실행하세요.
