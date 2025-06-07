CREATE TABLE IF NOT EXISTS book (
    id VARCHAR PRIMARY KEY,
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    price INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT uq_book_title_author UNIQUE (title, author)
);

CREATE INDEX IF NOT EXISTS idx_book_title_author ON book (title, author);
CREATE INDEX IF NOT EXISTS idx_book_author_title ON book (author, title);