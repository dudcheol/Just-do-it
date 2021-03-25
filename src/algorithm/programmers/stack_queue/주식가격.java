package algorithm.programmers.stack_queue;

import java.util.Stack;

// 22:45
public class 주식가격 {
    public int[] solution(int[] prices) {
        Stack<Integer> s = new Stack<>(); // 계속 상승하는 애들의 인덱스를 스택에 담음

        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            if (s.isEmpty()){ // 스택이 비어있으면
                s.push(i); // 상승하는 애가 완전히 끊겼던 것이므로 무조건 상승할 애가 나옴
                continue;
            }
            while (!s.isEmpty() && prices[s.peek()] > prices[i]){ // 현재보다 스택 top이 더 크다? => 하강 => 스택에서 빼내서 정답배열에 기록해준다
                answer[s.peek()] = i-s.pop(); // 인덱스의 차이가 얼만큼 상승해왔는지를 나타낸다
            }
            s.push(i); // 하강하던 시점들을 모두 처리했으므로 현재 인덱스를 넣어준다
        }


        int cnt=0;
        for (int i = answer.length-1; i >= 0; i--) {
            if (answer[i]==0){
                answer[i]=cnt;
            }
            cnt++;
        }

        return answer;
    }

    public static void main(String[] args) throws Exception{
       주식가격 c = new 주식가격();
       int[] prices = {1, 2, 3, 2, 3};
       int[] ans = c.solution(prices);
       for(int a : ans){
           System.out.println(a);
       }
    }
}
