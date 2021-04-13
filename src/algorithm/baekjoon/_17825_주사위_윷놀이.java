package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11:51~2:20 오답
public class _17825_주사위_윷놀이 {

//	private static int[] originMap = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 };
//	private static int[] map5 = { 0,0,0,0,0,0, 13, 16, 19, 25, 30, 35, 40 };
//	private static int[] map10 = { 0,0,0,0,0,0,0,0,0,0,0, 22, 24, 25, 30, 35, 40 };
//	private static int[] map15 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 28, 27, 26, 25, 30, 35, 40 };

	private static int[][] map = { { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 0, 0, 0, 0, 0, 0, 13, 16, 19, 25, 30, 35, 40 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 24, 25, 30, 35, 40 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 27, 26, 25, 30, 35, 40 }, };

	private static int[] unitCurIdx = { 0, 0, 0, 0 };
	private static int[] unitStartIdx = { 0, 0, 0, 0 };
	private static int[] sum = { 0, 0, 0, 0 };
	private static int[] cmd;
	private static int max;
	private static boolean[] finish = { false, false, false, false };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cmd = new int[10];
		for (int i = 0; i < 10; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}

		game(0);

		System.out.println(max);
	}

	private static void game(int stage) {
		System.out.println(stage==10?"last":cmd[stage]);
		System.out.println(Arrays.toString(unitCurIdx));
		System.out.println(Arrays.toString(sum));
		if (stage == 10 || allFinish()) { // stage 끝
			int s = 0;
			for (int i = 0; i < 4; i++) {
				s += sum[i];
			}
			max = Math.max(max, s);
			return;
		}

		loop: for (int num = 0; num < 4; num++) {
			if (finish[num])
				continue; // 이미 도착한 말이면 생략

			// 가야할 방향 정하기
			int dir = unitStartIdx[num];
			unitCurIdx[num] += cmd[stage]; // 말 이동

			// dir별로 도착지점 도착여부 파악
			if (isGoal(dir, unitCurIdx[num])) {
				// 도착했다면
				finish[num] = true;
//				sum[num] += 40;
				game(stage + 1);
//				sum[num] -= 40;
				finish[num] = false;
				unitCurIdx[num] -= cmd[stage]; // 말 이동 취소
				continue;
			}

			// 이미 말이 있는 곳에 이동하려 하는지 확인
			for (int i = 0; i < 4; i++) {
				if (i == num) // 자기 자신은 제외
					continue;
				if (unitCurIdx[i] == unitCurIdx[num] && !finish[i]) {
					// 이미 말이 있는 곳에 이동하려 했으니 이동취소
					unitCurIdx[num] -= cmd[stage];
					continue loop;
				}
			}

			// 아직 도착하지 못했다면
			sum[num] += map[dir][unitCurIdx[num]]; // 점수누적
			if (unitStartIdx[num] == 0) { // 방향 전환 한 적 없음
				if (unitCurIdx[num] == 5) { // 다음에파란방향으로 가야하는것 표시
					unitStartIdx[num] = 1;
				} else if (unitCurIdx[num] == 10) {
					unitStartIdx[num] = 2;
				} else if (unitCurIdx[num] == 15) {
					unitStartIdx[num] = 3;
				}
			}
			game(stage + 1);
			if (unitStartIdx[num] != 0) { // 방향 전환 한 적 있음 .. 좀 애매함
				if (unitCurIdx[num] == 5) { // 다음에파란방향으로 가야했던것 취소
					unitStartIdx[num] = unitStartIdx[num] == 1 ? 0 : unitStartIdx[num];
				} else if (unitCurIdx[num] == 10) {
					unitStartIdx[num] = unitStartIdx[num] == 2 ? 0 : unitStartIdx[num];
				} else if (unitCurIdx[num] == 15) {
					unitStartIdx[num] = unitStartIdx[num] == 3 ? 0 : unitStartIdx[num];
				}
			}
			sum[num] -= map[dir][unitCurIdx[num]]; // 유닛별 누적된 점수
			unitCurIdx[num] -= cmd[stage]; // 말 이동 취소

		}
	}

	private static boolean allFinish() {
		for (int i = 0; i < 4; i++) {
			if (!finish[i])
				return false;
		}
		return true;
	}

	private static boolean isGoal(int dir, int i) {
		if (dir == 0) {
			if (i > 20) {
				return true;
			}
		} else if (dir == 1) {
			if (i > 12) {
				return true;
			}
		} else if (dir == 2) {
			if (i > 16) {
				return true;
			}
		} else if (dir == 3) {
			if (i > 22) {
				return true;
			}
		}
		return false;
	}

}
