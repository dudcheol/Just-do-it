package algorithm.programmers.kakaoIntern2021;

import java.util.*;

public class 숫자_문자열과_영단어 {
	
	public int solution(String s) {
        
        HashMap<String, Integer> map = new HashMap();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            
            // 숫자, 문자 확인
            if('0'<=c-0&&c-0<='9'){ // 숫자라면
                ans.append(c);
            } else { // 문자라면
                sb.append(c);
                // System.out.println(sb);
                if(map.containsKey(sb.toString())){
                    ans.append(map.get(sb.toString()));
                    sb.setLength(0);
                    // System.out.println(ans);
                }
            }
        }
        
        int answer = Integer.parseInt(ans.toString());
        // int answer =0;
        return answer;
    }

	public static void main(String[] args) {
	    
	}

}
