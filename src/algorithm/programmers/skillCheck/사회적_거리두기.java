package algorithm.programmers.skillCheck;


import java.util.ArrayList;
import java.util.List;

public class 사회적_거리두기 {

    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length+1];

        List<Integer> list = new ArrayList<>(); // 방에 있는 사람 리스트
        int idx = 0;
        for (int i = 0; i < leave.length; i++) {
            // 퇴실할 사람이 방에 이미 있는 사람이면 그냥 뺀다
            if (list.contains(new Integer(leave[i]))){
                list.remove(new Integer(leave[i]));
                continue;
            }

            // 퇴실한 사람 i를 입실정보로 찾고 찾기까지 입실했던 사람 모두 기억하기
            while (true) {
                if (leave[i] == enter[idx]) break;
                list.add(enter[idx]);
                idx++;
            }

            for(int l : list){
                answer[l]+=list.size();
            }
            answer[leave[i]]+=list.size();

            idx++;
        }

        int[] ret = new int[answer.length-1];
        for (int i = 0; i < answer.length-1; i++) {
            ret[i] = answer[i+1];
        }

        return ret;
    }

    public static void main(String[] args) {
        사회적_거리두기 c = new 사회적_거리두기();
//        int[] enter = {1, 3, 2};
//        int[] leave = {1, 2, 3};
//        int[] enter = {1, 4, 2, 3};
//        int[] leave = {2, 1, 3, 4};
//        int[] enter = {3,2,1};
//        int[] leave = {2, 1, 3};
//        int[] enter = {3,2,1};
//        int[] leave = {1, 3,2};
        int[] enter = {1,4,2,3};
        int[] leave = {2,1,4,3};
        int[] ans = c.solution(enter, leave);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
    }
}
