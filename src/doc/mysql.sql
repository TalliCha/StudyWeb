drop TABLE numbers;
drop TABLE lottolist;

create table lottolist(
	idx INT not null auto_increment,
	inputdate DATETIME not null DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(idx) 
)engine=InnoDB DEFAULT charset=utf8;

create table numbers(
	idx INT not null,
	num INT not null,
	PRIMARY KEY  (idx), 
	CONSTRAINT fk_idx 
     FOREIGN KEY (idx) REFERENCES lottolist (idx) 
	 ON DELETE CASCADE 
	 ON UPDATE CASCADE 
)engine=InnoDB DEFAULT charset=utf8;