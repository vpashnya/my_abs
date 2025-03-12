--PG tables
create table client(
                    id 			int 			generated by default as identity primary key
				,	full_name 	varchar(255)	not null
				,	inn 		varchar(12)   	unique
				);


create table account(
                    id 			int 			generated by default as identity primary key
                ,   acc_type    int             not null
                ,   acc_num     varchar(50)     not null unique
                ,   client      int             not null references client(id)
                );

create table pay_document(
                    id 			int 			generated by default as identity primary key
                ,   debet       int             not null references account(id)
                ,   credit      int             not null references account(id)
                ,   doc_sum     decimal(18,2)   not null
                ,   doc_date    date            not null
                ,   state       int             not null
                );

create table fin_record(
                    id 			int 			generated by default as identity primary key
                ,   account     int             not null references account(id)
                ,   rec_date    date            not null
                ,   rec_sum     decimal(18,2)   not null
                ,   rec_type    int             not null
                ,   pay_document    int         not null references pay_document(id)
                );



create table current_bank_info( id 			    int 			generated by default as identity primary key
                            ,   oper_day        date            not null
                            ,   corr_account    int             not null references account(id)
                            ,   bank_client     int             not null references client(id)
                            ,   bank_code       varchar(255)    not null
                            );