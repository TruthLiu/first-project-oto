create table merchant_apply_record(
	id varchar2(40) primary key,
	m_name varchar2(24),
	idcard varchar2(64),
	heading varchar2(64),
	address varchar2(30),
	status number(2),
	ban char(1),
	bcontent varchar2(128),
	m_id varchar2(40) not null
)

drop table merchant_apply_record;