--���ݿ��ʼ���ű�

--�������ݿ�
create database seckill;
--ʹ�����ݿ�
use seckill;
--������ɱ����
create table seckill(
	seckill_id bigint not null auto_increment commit '��Ʒ���id',
	name varchar(120) not null comment '��Ʒ����',
	number int not null comment '�������',
	start_time timestamp not null comment '��ɱ����ʱ��',
	end_time timestamp not null comment '��ɱ����ʱ��',
	create_time timestamp not null default current_timestamp comment '����ʱ��',
	primary key (seckill_id),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000 default charset=gbk comment='��ɱ����'

--��ʼ������
insert into seckill(name,number,start_time,end_time)
values
('1000Ԫ��ɱiphone6',100,'2017-03-21 00:00:00','2017-03-23 00:00:00'),
('500Ԫ��ɱipad2',200,'2017-03-22 00:00:00','2017-03-24 00:00:00'),
('300Ԫ��ɱС��4',300,'2017-03-24 00:00:00','2017-03-25 00:00:00'),
('200Ԫ��ɱ����note',400,'2017-03-25 00:00:00','2017-03-26 00:00:00'),
('200Ԫ��ɱ����note',400,'2017-03-26 00:00:00','2017-03-27 00:00:00');

--��ɱ�ɹ���ϸ��
--�û���½��֤��ص���Ϣ
create table success_killed(
	seckill_id bigint not null comment '��ɱ��Ʒid',
	user_phone bigint not null comment '�û��ֻ���',
	state tinyint not null  default -1 comment '״̬��ʾ��-1��Ч 0���ɹ� 1���Ѹ��� 2���ѷ���',
	create_time timestamp not null comment '����ʱ��',
	primary key(seckill_id,user_phone),/*��������*/
	key idx_create_time(create_time)
)engine=InnoDB default charset=gbk comment='��ɱ�ɹ���ϸ��'

--�������ݿ����̨
mysql -uroot -p
MCC0420