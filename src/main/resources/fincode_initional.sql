do $$
declare
	rBankClient 	int;
	sBankName 		varchar(255) := 'Банк "Рога и Копыта" филиал в Васюках';
	sBankInn		varchar(255) := '7777666655';

	rCorrAcc 	int;
	sCorrNum	varchar(255) := '30102810000000000001';

	rCurrentBakInfo	int;
	sBankCode 		varchar(255) := '000000123';
begin
	delete from current_bank_info cascade;
	delete from account cascade;
	delete from client cascade;


	insert into client(full_name, inn) values(sBankName, sBankInn) returning id into rBankClient;

	insert into account(acc_type, acc_num, client) values(2, sCorrNum, rBankClient ) returning id  into rCorrAcc;

	insert into current_bank_info(oper_day, corr_account, bank_client, bank_code)
				values(to_date('01.01.2025','dd.mm.yyyy'), rCorrAcc, rBankClient, sBankCode) returning id into rCurrentBakInfo;


	commit;
end;
$$
