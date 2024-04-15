create table movies(
	id int primary key,
    title varchar(50) not null,
    backdrop_path varchar(100) not null,
    overview varchar(500) not null,
    popularity double,
    release_date date,
    video boolean,
    vote_average double,
	vote_count int
);