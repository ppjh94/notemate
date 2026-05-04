create table user (
	user_id bigint auto_increment primary key,
	email varchar(100) not null unique,
	password varchar(255) not null,
	name varchar(50) not null,
	roll varchar(20) not null default 'USER',
	created_at datetime not null,
	updated_at datetime not null
);

create table contents (
	content_id bigint auto_increment primary key,
	user_id bigint not null,
	title varchar(200) not null,
	body text not null,
	is_premium boolean not null,
	created_at datetime not null,
	updated_at datetime not null,
	constraint fk_contents_author 
		foreign key (user_id) references user(user_id)
);

create table subscriptionPlan (
	plan_id bigint auto_increment primary key,
	plan_name varchar(50) not null,
	price int not null,
	duration_days int not null,
	description varchar(255),
	is_active boolean not null,
	created_at datetime not null,
	updated_at datetime not null
);

create table subscription (
	status_id bigint auto_increment primary key,
	user_id bigint not null,
	plan_id bigint not null,
	status varchar(20) not null,
	start_at datetime,
	end_at datetime,
	created_at datetime not null,
	updated_at datetime not null,
	constraint fk_subscription_user
		foreign key (user_id) references user(user_id),
	constraint fk_subscription_plan
		foreign key (plan_id) references subscriptionPlan(plan_id)
);

create table payment (
	payment_id bigint not null,
	user_id bigint not null,
	status_id bigint not null,
	order_id varchar(100) not null unique,
	payment_key varchar(200) not null,
	amount int not null,
	status varchar(20) not null,
	requested_at datetime,
	approved_at datetime,
	created_at datetime not null,
	updated_at datetime not null,
	constraint fk_payment_user
		foreign key (user_id) references user(user_id),
	constraint fk_payment_plan
		foreign key (status_id) references subscription(status_id)
);

