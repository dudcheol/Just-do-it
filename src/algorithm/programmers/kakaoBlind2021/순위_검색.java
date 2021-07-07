package algorithm.programmers.kakaoBlind2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 순위_검색 {
	private static HashMap<String, List<Integer>> map;

    public int[] solution(String[] info, String[] query) {
    	map = new HashMap<>();
    	
    	// 주어진 info로 만들 수 있는 모든 경우를 키로 가지는 map 생성
    	for (int i = 0; i < info.length; i++) {
    		dfs("",info[i].split(" "),0);
		}
    	
    	// map에 담긴 리스트들 모두 정렬
    	for (List<Integer> m : map.values()) {
    		m.sort((a,b)->{
    			return a-b;
    		});
		}
    	
    	// 쿼리 파싱
    	List<Integer> list = new ArrayList<>();
    	for (int i = 0; i < query.length; i++) {
			String[] q = query[i].replaceAll(" and ", " ").split(" ");
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 4; j++) {
				sb.append(q[j]);
			}
			String key = sb.toString();
			int score = Integer.parseInt(q[4]);
			
			List<Integer> result = map.getOrDefault(key, new ArrayList<>());
			
			// 이분탐색으로 score보다 큰 index 찾기
			int start=0;
			int end=result.size()-1;
			while(true) {
				if(start > end) break;
				int mid = (start + end)/2;
				if(score > result.get(mid)) {
					start = mid+1;
				} else {
					end = mid-1;
				}
			}
			
			list.add(result.size()-start);
		}
    	
    	int[] answer = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
			answer[i]=list.get(i);
		}
    	
    	return answer;
    }

    private void dfs(String str, String[] ary, int k) {
		if(k==4) {
			int value = Integer.parseInt(ary[4]);
			if (map.containsKey(str)) {
				map.get(str).add(value);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(value);
				map.put(str, list);
			}
			return;
		}
		dfs(str+"-",ary,k+1);
		dfs(str+ary[k],ary,k+1);
	}

	public static void main(String[] args) throws Exception{
        순위_검색 c = new 순위_검색();

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        for(int i : c.solution(info, query)){
            System.out.println(i);
        }
    }
}
