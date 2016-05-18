package vo;

import java.util.Set;
import java.util.TreeSet;

public final class LottoVo {
	private Set<Integer> lotto;

	public LottoVo() {
		lotto = new TreeSet<>();
	}

	public Set<Integer> getLotto() {
		return lotto;
	}

	public void setLotto(Set<Integer> lotto) {
		if (lotto.size() == 6) {
			this.lotto = lotto;
		} else {
			System.out.println("Lotto 갯수가 6개가 아닙니다.");
		}
	}

}