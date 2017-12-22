CREATE TABLE users (
  id       INTEGER PRIMARY KEY auto_increment,
  name     VARCHAR,
  password VARCHAR
);

CREATE TABLE tags (
  id   INTEGER PRIMARY KEY auto_increment,
  name VARCHAR
);

CREATE TABLE users_tags (
  tag_id  INTEGER,
  user_id INTEGER
);

CREATE TABLE notes (
  id           INTEGER PRIMARY KEY auto_increment,
  name         VARCHAR,
  text         TEXT,
  notebook_id  INTEGER,
  user_id      INTEGER,
  created_date TIMESTAMP,
  updated_date TIMESTAMP
);

CREATE TABLE notes_tags (
  note_id INTEGER,
  tag_id  INTEGER
);

CREATE TABLE notebooks (
  id      INTEGER PRIMARY KEY auto_increment,
  name    VARCHAR,
  user_id INTEGER
);

ALTER TABLE users_tags
  ADD CONSTRAINT "users_tags_fk0" FOREIGN KEY (tag_id) REFERENCES tags (id);
ALTER TABLE users_tags
  ADD CONSTRAINT "users_tags_fk1" FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE notes_tags
  ADD CONSTRAINT "notes_tags_fk0" FOREIGN KEY (tag_id) REFERENCES tags (id);
ALTER TABLE notes_tags
  ADD CONSTRAINT "notes_tags_fk1" FOREIGN KEY (note_id) REFERENCES notes (id);

ALTER TABLE notebooks
  ADD CONSTRAINT "notebooks_fk0" FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE notes
  ADD CONSTRAINT "notes_fk0" FOREIGN KEY (notebook_id) REFERENCES notebooks (id);

ALTER TABLE notes
  ADD CONSTRAINT "notes_fk1" FOREIGN KEY (user_id) REFERENCES users (id);

-- id       INTEGER PRIMARY KEY auto_increment,
-- name     VARCHAR,
-- password VARCHAR
INSERT INTO users VALUES (0, 'Ivan0', 'Password');
INSERT INTO users VALUES (1, 'Ivan1', 'Password');
INSERT INTO users VALUES (2, 'Ivan2', 'Password');
INSERT INTO users VALUES (3, 'Ivan3', 'Password');

-- id   INTEGER PRIMARY KEY auto_increment,
-- name VARCHAR
INSERT INTO tags VALUES (0, 'tag0');
INSERT INTO tags VALUES (1, 'tag1');
INSERT INTO tags VALUES (2, 'tag2');
INSERT INTO tags VALUES (3, 'tag3');

-- id      INTEGER PRIMARY KEY auto_increment,
-- name    VARCHAR,
-- user_id INTEGER
INSERT INTO notebooks VALUES (0, 'notebook 0', 0);
INSERT INTO notebooks VALUES (1, 'notebook 0.1', 0);
INSERT INTO notebooks VALUES (2, 'notebook 1', 1);
INSERT INTO notebooks VALUES (3, 'notebook 2', 1);
INSERT INTO notebooks VALUES (4, 'notebook 3', 2);
INSERT INTO notebooks VALUES (5, 'notebook 3', 2);

-- id           INTEGER PRIMARY KEY auto_increment,
-- name         VARCHAR,
-- text         TEXT,
-- notebook_id  INTEGER,
-- user_id      INTEGER,
-- created_date TIMESTAMP,
-- updated_date TIMESTAMP
INSERT INTO notes VALUES (0, 'note 0', 'text 0', 0, 0, '2017-12-12', '2017-12-12');
INSERT INTO notes VALUES (1, 'note 1', 'text 1', 1, 0, '2017-12-12', '2017-12-12');
INSERT INTO notes VALUES (2, 'note 2', 'text 2', 2, 1, '2017-11-12', '2017-12-12');
INSERT INTO notes VALUES (3, 'note 3', 'text 3', 3, 1, '2017-10-12', '2017-11-12');
INSERT INTO notes VALUES (4, 'note 4', 'text 4', 4, 2, '2017-11-12', '2017-12-12');
INSERT INTO notes VALUES (5, 'note 5', 'text 5', 5, 2, '2017-10-12', '2017-11-12');
INSERT INTO notes VALUES (6, 'note 6', 'text 6', 0, 0, '2017-11-12', '2017-12-12');
INSERT INTO notes VALUES (7, 'note 7', 'text 7', 1, 0, '2017-10-12', '2017-11-12');
INSERT INTO notes VALUES (8, 'note 8', 'text 8', 2, 1, '2017-11-12', '2017-12-12');
INSERT INTO notes VALUES (9, 'note 9', 'text 9', 3, 1, '2017-10-12', '2017-11-12');

INSERT INTO users_tags VALUES (0, 0);
INSERT INTO users_tags VALUES (1, 1);
INSERT INTO users_tags VALUES (2, 1);
INSERT INTO users_tags VALUES (2, 2);
INSERT INTO users_tags VALUES (1, 2);
INSERT INTO users_tags VALUES (3, 3);

INSERT INTO notes_tags VALUES (0, 0);
INSERT INTO notes_tags VALUES (4, 1);
INSERT INTO notes_tags VALUES (4, 2);
INSERT INTO notes_tags VALUES (5, 2);
INSERT INTO notes_tags VALUES (0, 0);
INSERT INTO notes_tags VALUES (1, 0);
