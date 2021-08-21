package algorithm.codility;

public class MinMaxDivision {

    public static int solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        // 이분탐색을 통해 최대합 정하기
        int maxsum = A.length * M; // 가능한 최대 합
        int start = 0;
        int end = maxsum;
        int answer = maxsum;
        while (start <= end) {
            int mid = (start + end) / 2;

            // 가능한 값인지 확인하는 로직
            int sum = 0;
            int max = 0;
            int cnt = 1; // 그룹의 수
            for (int i = 0; i < A.length; i++) {
                if (sum + A[i] > mid) {
                    max = Math.max(max, sum);
                    cnt++;
                    sum = A[i];
                } else {
                    sum += A[i];
                }
            }
            max = Math.max(max, sum);

            if (cnt <= K) { // 가능하다면 더 줄여본다
                answer = max;
                end = mid - 1;
            } else { // 불가능
                start = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(MinMaxDivision.solution(3,5, new int[]{2, 1, 5, 1, 2, 2, 2}));
    }

}
