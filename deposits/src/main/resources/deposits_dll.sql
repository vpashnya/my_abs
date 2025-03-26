create table deposit(
                         id 			 int 	        AUTO_INCREMENT
                    ,    client_ext_id   int            not null
                    ,    client_name     varchar(200)   not null
                    ,    account_ext_id  int            not null
                    ,    account_num     varchar(200)   not null
                    ,    amount          decimal(18,2)  not null
                    ,    rate            decimal(18,2)  not null
                    ,    duration        int            not null
                    ,    date_open       date           not null
                    ,    status          varchar(20)    not null
                    ,    pay_to          varchar(20)    not null
                    ,	primary key(id)
                )


create table deposit_operation( id 			    int 	    AUTO_INCREMENT
                            ,   deposit         int             not null      references deposit(id)
                            ,   oper_date       date            not null
                            ,   oper_sum        decimal(18,2)   not null
                            ,   pay_doc_id      int
                            ,   cash_oper_id    int
                            ,   purpose         varchar(2000)   not null
                            ,   state           varchar(20)     not null
                            ,   refuse_reason   varchar(2000)
                            ,	primary key(id)
                            )

