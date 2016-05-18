drop sequence lotto_idx;
drop TABLE numbers;
drop TABLE lottolist;

create sequence lotto_idx start with 1  increment BY 1 nomaxvalue;

create table lottolist(
	idx NUMBER not null,
	inputdate DATE DEFAULT SYSDATE,
	CONSTRAINT PK_idx PRIMARY KEY(idx)
);

create table numbers(
	idx NUMBER not null,
	num NUMBER not null,
	CONSTRAINT fk_idx 
     FOREIGN KEY (idx) REFERENCES lottolist (idx) 
	 ON DELETE CASCADE 
);