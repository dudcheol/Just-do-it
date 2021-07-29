package algorithm.programmers.binarySearch;

import java.util.*;

public class 입국심사 {

	public long solution(int n, int[] times) {
		Arrays.sort(times);

		// 이분탐색을 통해 추정 시간값을 구함
		long start = 1;
		long end = (long) times[times.length - 1] * n; // 가장 오래 걸리는 심사위원이 걸릴 수 있는 최대 시간
		
		while (start <= end) {
			long mid = (start + end) / 2;

			long cnt = 0;
			for (int i = 0; i < times.length; i++) {
				long time = times[i];
				cnt += mid / time; // 심사관이 mid시간안에 심사할 수 있는 사람의 수
			}
			if (cnt >= n) { // 임국심사를 기다리는 사람의 수보다 많은 사람을 심사할 수 있다면
				end = mid - 1;// mid 값을 줄여서 더 적은 시간에도 할 수 있는지 확인
			} else {
				start = mid + 1;
			}
		}

		return start;
	}

	public static void main(String[] args) {
		
	}
}
