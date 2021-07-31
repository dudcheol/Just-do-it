package algorithm.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크_컨트롤러 {

    public int solution(int[][] jobs) {

        Arrays.sort(jobs, (o1, o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });

        // 작업시간이 짧은 우선순위
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });

        int curtime = 0;
        // int curtime = jobs[0][0]; // 왜??
        int cnt = 0; // 수행한 작업 수
        int sum = 0;
        while (true) {

            //현재 시간 이하의 요청을 모두 작업 큐에 넣음
            while (true) {
                if (cnt < jobs.length && jobs[cnt][0] <= curtime) {
                    pq.offer(jobs[cnt]);
                    cnt++;
                } else {
                    break;
                }
            }

            if (pq.isEmpty()) { // 작업큐가 비어있다면
                if (cnt < jobs.length) {
                    // 다음 작업의 요청시간으로 현재 시간을 변경 후 다시 작업 큐 넣는 작업 시도
                    curtime = jobs[cnt][0];
                    continue;
                } else {
                    break;
                }
            }

            // 작업 큐에서 작업시간이 가장 짧은 작업을 꺼내 작업을 실시함
            int[] job = pq.poll();
            curtime += job[1]; // 현재 시간에 지금 진행할 작업의 작업 시간을 더해줌
            sum += curtime - job[0]; // 대기시간+작업시간 누적
        }

        int answer = sum / jobs.length;
        return answer;
    }

    public static void main(String[] args) {

    }
}
