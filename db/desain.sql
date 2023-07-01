create table tscore(
    username varchar(50) unique,
    score int default 0,
    standing int default 0
);