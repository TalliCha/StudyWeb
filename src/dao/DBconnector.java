package dao;

import service.LottoService;

public class DBconnector {
	public static LottoService mysqlConn() {
		return new LottoService(new LottoMysqlDao());
	}

	public static LottoService oracleConn() {
		return new LottoService(new LottoOracleDao());
	}
}
