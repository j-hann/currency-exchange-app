-- 테이블 생성
CREATE TABLE `User` (
                        `id`	bigint	NOT NULL	COMMENT '사용자 고유 식별자',
                        `email`	varchar(255)	NOT NULL	COMMENT 'unique id',
                        `name`	varchar(10)	NOT NULL	COMMENT '사용자 이름',
                        `created_at`	datetime(6)	NOT NULL	COMMENT '최초 생성일 (yyyy-mm-dd : hh-mm-ss)',
                        `modified_at`	datetime(6)	NOT NULL	COMMENT '최근 수정일  (yyyy-mm-dd : hh-mm-ss)'
);

CREATE TABLE `Exchange` (
                            `id`	bigint	NOT NULL	COMMENT '환전 요청 고유 식별자',
                            `user_id`	bigint	NOT NULL	COMMENT '사용자 고유 식별자',
                            `currency_id`	bigint	NOT NULL	COMMENT '환전 대상 통화 식별자',
                            `amount_before_exchange`	varchar(20)	NOT NULL	COMMENT '원화 기준',
                            `amount_after_exchange`	varchar(20)	NOT NULL,
                            `status`	enum	NOT NULL	DEFAULT 1	COMMENT '정상(normal) : 1,  취소(cancelled) : 0',
                            `created_at`	datetime(6)	NOT NULL	COMMENT '최초 생성일 (yyyy-mm-dd : hh-mm-ss)',
                            `modified_at`	datetime(6)	NOT NULL	COMMENT '최근 수정일  (yyyy-mm-dd : hh-mm-ss)'
);

CREATE TABLE `Currency` (
                            `id`	bigint	NOT NULL	COMMENT '통화 고유 식별자',
                            `currency_status`	enum	NOT NULL	COMMENT '국가별 통화 코드 & 이름 & 기호',
                            `exchange_rate`	Decimal	NOT NULL,
                            `created_at`	datetime(6)	NOT NULL	COMMENT '최초 생성일 (yyyy-mm-dd : hh-mm-ss)',
                            `modified_at`	datetime(6)	NOT NULL	COMMENT '최근 수정일  (yyyy-mm-dd : hh-mm-ss)'
);

-- 제약조건
ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                                         `id`
    );

ALTER TABLE `Exchange` ADD CONSTRAINT `PK_EXCHANGE` PRIMARY KEY (
                                                                 `id`
    );

ALTER TABLE `Currency` ADD CONSTRAINT `PK_CURRENCY` PRIMARY KEY (
                                                                 `id`
    );

ALTER TABLE `Exchange` ADD CONSTRAINT `FK_User_TO_Exchange_1` FOREIGN KEY (
                                                                           `user_id`
    )
    REFERENCES `User` (
                       `id`
        );

ALTER TABLE `Exchange` ADD CONSTRAINT `FK_Currency_TO_Exchange_1` FOREIGN KEY (
                                                                               `currency_id`
    )
    REFERENCES `Currency` (
                           `id`
        );

