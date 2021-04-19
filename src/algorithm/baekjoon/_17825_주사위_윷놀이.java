package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11:51~2:20 오답
// 2차풀이 ... 성공!!
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
//		System.out.println(stage==10?"last":cmd[stage]);
//		System.out.println(Arrays.toString(unitCurIdx));
//		System.out.println(Arrays.toString(sum));
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
				game(stage + 1);
				finish[num] = false;
				unitCurIdx[num] -= cmd[stage]; // 말 이동 취소
				continue;
			}

			// 이미 말이 있는 곳에 이동하려 하는지 확인
			for (int i = 0; i < 4; i++) {
				if (i == num) // 자기 자신은 제외
					continue;
				// 이미 말이 있는 곳인지 확인
				// 이미 말이 있는 곳에 이동하려 했으니 이동취소
				if (unitStartIdx[i] == unitStartIdx[num]) { // 같은 경로이고 curidx같으면 겹치는 경우임
					if (unitCurIdx[i] == unitCurIdx[num]) {
						unitCurIdx[num] -= cmd[stage];
						continue loop;
					}
				} else { // 다른경로라면 경로별로 특정 idx 이상만 확인하면 됨
					// 5,10,15일때 앞에서 이미 startIdx를 바꾸어 줬으므로 5,10,15의 분기는 따로 신경써줘야함
					int idxNum = matchIdx(unitStartIdx[num], unitCurIdx[num]);
					int idxI = matchIdx(unitStartIdx[i], unitCurIdx[i]);
					if (idxI > 0 && idxNum > 0 && idxI == idxNum) {
						unitCurIdx[num] -= cmd[stage];
						continue loop;
					}
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

	private static int matchIdx(int startIdx, int curIdx) {
		switch (startIdx) {
		case 0:
			if (curIdx == 20)
				return 22;
//			else
//				return curIdx;
			else if(curIdx==5) return 5;
			else if(curIdx==10) return 10;
			else if(curIdx==15) return 15;
			else return -1;
		case 1:
			if (curIdx == 5)
				return curIdx;
			if (curIdx < 9)
				return -1;
			else
				return curIdx + 10;
		case 2:
			if (curIdx == 10)
				return curIdx;
			if (curIdx < 13)
				return -1;
			else
				return curIdx + 6;
		case 3:
			if (curIdx == 15)
				return curIdx;
			if (curIdx < 19)
				return -1;
			else
				return curIdx;
		}
		return curIdx;
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