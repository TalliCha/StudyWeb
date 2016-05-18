package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import snaq.db.DBPoolDataSource;
import vo.AnalysisVo;
import vo.LottoVo;

public class LottoOracleDao implements LottoDao {

	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracl:thin:@localhost:1521:orcl";
	private final String user = "talli";
	private final String passwd = "1234";

	private final int minPool = 10;
	private final int maxPool = 30;
	private final int maxsize = 60;
	private final int idleTimeout = 2600;

	DBPoolDataSource bds;
	public LottoOracleDao() {
		this.bds = new DBPoolDataSource();
		this.bds.setDriverClassName(driver);
		this.bds.setUser(user);
		this.bds.setPassword(passwd);
		this.bds.setUrl(url);
		this.bds.setMinPool(minPool);
		this.bds.setMaxPool(maxPool);
		this.bds.setMaxSize(maxsize);
		this.bds.setIdleTimeout(idleTimeout); // Specified in seconds.

	}

	private Connection connect() throws SQLException {
		Connection conn = bds.getConnection();
		return conn;
	}

	@Override
	public int insert(List<LottoVo> lottoList) {

		int count = 0;

		for (LottoVo lotto : lottoList) {

			String sql = "INSERT INTO lottolist(idx) VALUES (lotto_idx.NEXTVAL)";
			count += sqlUpdate(sql);
			for (Integer num : lotto.getLotto()) {
				sql = "INSERT INTO numbers(idx,num) SELECT MAX(idx), " + num + " from lottolist ";
				sqlUpdate(sql);
				System.out.println(count+"/"+lottoList.size());
			}
		}
		return count;

	}

	@Override
	public int delete() {
		String sql = "DELETE FROM lottolist";

		return sqlUpdate(sql);
	}

	@Override
	public Map<Integer, LottoVo> select() {
		String sql = "SELECT * FROM NUMBERS ORDER BY idx ASC";

		return sqlSelectQuery(sql);
	}

	@Override
	public AnalysisVo analysis() {
		String sql = "SELECT  num , COUNT(num) count_num  FROM numbers GROUP BY num ORDER BY count_num DESC ";
		return analysisSqlQuery(sql);
	}

	private int sqlUpdate(String sql) {
		try (Connection conn = connect(); Statement stmt = conn.createStatement();) {
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	private Map<Integer, LottoVo> sqlSelectQuery(String sql) {
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			Map<Integer, LottoVo> lottoMap = new TreeMap<Integer, LottoVo>();

			while (rs.next()) {
				int idx = rs.getInt("idx");
				int num = rs.getInt("num");

				if (!lottoMap.containsKey(idx))
					lottoMap.put(idx, new LottoVo());

				lottoMap.get(idx).getLotto().add(num);
			}
			return lottoMap;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private AnalysisVo analysisSqlQuery(String sql) {
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			AnalysisVo analysisVo = new AnalysisVo();
			while (rs.next()) {
				int num = rs.getInt("num");
				int count_num = rs.getInt("count_num");

				analysisVo.getSortedNum().add(num);
				analysisVo.getNumMap().put(num, count_num);
			}
			return analysisVo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
