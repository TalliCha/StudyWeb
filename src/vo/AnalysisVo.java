package vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisVo {
	
	List<Integer> sortedNum;
	Map<Integer, Integer> numMap;
	public AnalysisVo() {
		sortedNum = new ArrayList<>();
		numMap = new HashMap<>();
	}
	public List<Integer> getSortedNum() {
		return sortedNum;
	}
	public void setSortedNum(List<Integer> sortedNum) {
		this.sortedNum = sortedNum;
	}
	public Map<Integer, Integer> getNumMap() {
		return numMap;
	}
	public void setNumMap(Map<Integer, Integer> numMap) {
		this.numMap = numMap;
	}
}
