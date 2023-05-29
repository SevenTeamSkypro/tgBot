ALTER TABLE cat_owners
    ADD COLUMN chat_id BIGINT;
ALTER TABLE dog_owners
    ADD COLUMN chat_id BIGINT;
ALTER TABLE clients
    ADD COLUMN chat_id BIGINT;
ALTER TABLE volunteers
    ADD COLUMN chat_id BIGINT;