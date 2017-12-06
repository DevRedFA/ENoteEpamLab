CREATE TABLE USERS (
  id       INTEGER PRIMARY KEY,
  name     VARCHAR,
  password VARCHAR
);

CREATE TABLE TAGS (
  id   INTEGER PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE USERS_TAGS (
  tag_id  INTEGER,
  user_id INTEGER
);

-- CREATE TABLE notes (
--   id          INTEGER PRIMARY KEY,
--   text        TEXT,
--   notebook_id INTEGER,
--   user_id     INTEGER,
--   created     TIMESTAMP,
--   updated     TIMESTAMP
-- );
--
-- CREATE TABLE notes_tags (
--   note_id INTEGER,
--   tag_id  INTEGER
-- );
--
-- CREATE TABLE notebooks (
--   id      INTEGER PRIMARY KEY,
--   name    VARCHAR,
--   user_id INTEGER
-- );


ALTER TABLE USERS_TAGS
  ADD CONSTRAINT "users_tags_fk0" FOREIGN KEY (tag_id) REFERENCES TAGS (id);
ALTER TABLE USERS_TAGS
  ADD CONSTRAINT "users_tags_fk1" FOREIGN KEY (user_id) REFERENCES USERS (id);

-- ALTER TABLE "notes"
--   ADD CONSTRAINT "notes_fk0" FOREIGN KEY ("notebook_id") REFERENCES "notebooks" ("id");
-- ALTER TABLE "notes"
--   ADD CONSTRAINT "notes_fk1" FOREIGN KEY ("user_id") REFERENCES "users" ("id");
--
-- ALTER TABLE "notes_tags"
--   ADD CONSTRAINT "notes_tags_fk0" FOREIGN KEY ("note_id") REFERENCES "notes" ("id");
-- ALTER TABLE "notes_tags"
--   ADD CONSTRAINT "notes_tags_fk1" FOREIGN KEY ("tag_id") REFERENCES "tags" ("id");
--
-- ALTER TABLE "notebooks"
--   ADD CONSTRAINT "notebooks_fk0" FOREIGN KEY ("user_id") REFERENCES "users" ("id");
