package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dao.LottoDao;
import vo.AnalysisVo;
import vo.LottoVo;

public class LottoService {

	LottoDao dao;

	public LottoService(LottoDao dao) {
		this.dao = dao;
	}

	public int setLottos(int round) {
		int insertCount = dao.insert(makeLottoList(round));
		System.out.println(insertCount + "개 : 로또 추가");
		return insertCount;
	}

	public Map<Integer, LottoVo> getLottos() {
		Map<Integer, LottoVo> lottoMap = dao.select();
		System.out.println(lottoMap.size() + "개 : 로또 검색");

		for (Entry<Integer, LottoVo> entry : lottoMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().getLotto());
		}
		return lottoMap;
	}

	public int deleteAll() {
		int deleteCount = dao.delete();
		System.out.println(deleteCount + " 개 : 로또 삭제");
		return deleteCount;
	}

	public AnalysisVo analysis() {
		AnalysisVo analysisList = dao.analysis();
		for (Integer num : analysisList.getSortedNum()) {
			System.out.println(num + " 번 :  " + analysisList.getNumMap().get(num) + " 개");
		}

		return analysisList;
	}

	/*----------------------------------------------------------------*/

	private List<LottoVo> makeLottoList(int round) {

		List<LottoVo> lottoList = new ArrayList<>();
		for (int i = 0; i < round; i++)
			lottoList.add(makeLotto());

		return lottoList;

	}

	private LottoVo makeLotto() {
		LottoVo lotto = new LottoVo();

		while (lotto.getLotto().size() < 6)
			lotto.getLotto().add((int) (Math.random() * 45 + 1));

		return lotto;
	}

}
