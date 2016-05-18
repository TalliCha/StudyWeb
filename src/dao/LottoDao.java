package dao;

import java.util.List;
import java.util.Map;

import vo.AnalysisVo;
import vo.LottoVo;

public interface LottoDao {
	public int insert(List<LottoVo> lottoList);

	public int delete();

	public Map<Integer, LottoVo> select();

	public AnalysisVo analysis();
}
