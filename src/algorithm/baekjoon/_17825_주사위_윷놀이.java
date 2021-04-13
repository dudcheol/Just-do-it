package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11:51~2:20 ����
public class _17825_�ֻ���_������ {

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
		if (stage == 10 || allFinish()) { // stage ��
			int s = 0;
			for (int i = 0; i < 4; i++) {
				s += sum[i];
			}
			max = Math.max(max, s);
			return;
		}

		loop: for (int num = 0; num < 4; num++) {
			if (finish[num])
				continue; // �̹� ������ ���̸� ����

			// ������ ���� ���ϱ�
			int dir = unitStartIdx[num];
			unitCurIdx[num] += cmd[stage]; // �� �̵�

			// dir���� �������� �������� �ľ�
			if (isGoal(dir, unitCurIdx[num])) {
				// �����ߴٸ�
				finish[num] = true;
//				sum[num] += 40;
				game(stage + 1);
//				sum[num] -= 40;
				finish[num] = false;
				unitCurIdx[num] -= cmd[stage]; // �� �̵� ���
				continue;
			}

			// �̹� ���� �ִ� ���� �̵��Ϸ� �ϴ��� Ȯ��
			for (int i = 0; i < 4; i++) {
				if (i == num) // �ڱ� �ڽ��� ����
					continue;
				if (unitCurIdx[i] == unitCurIdx[num] && !finish[i]) {
					// �̹� ���� �ִ� ���� �̵��Ϸ� ������ �̵����
					unitCurIdx[num] -= cmd[stage];
					continue loop;
				}
			}

			// ���� �������� ���ߴٸ�
			sum[num] += map[dir][unitCurIdx[num]]; // ��������
			if (unitStartIdx[num] == 0) { // ���� ��ȯ �� �� ����
				if (unitCurIdx[num] == 5) { // �������Ķ��������� �����ϴ°� ǥ��
					unitStartIdx[num] = 1;
				} else if (unitCurIdx[num] == 10) {
					unitStartIdx[num] = 2;
				} else if (unitCurIdx[num] == 15) {
					unitStartIdx[num] = 3;
				}
			}
			game(stage + 1);
			if (unitStartIdx[num] != 0) { // ���� ��ȯ �� �� ���� .. �� �ָ���
				if (unitCurIdx[num] == 5) { // �������Ķ��������� �����ߴ��� ���
					unitStartIdx[num] = unitStartIdx[num] == 1 ? 0 : unitStartIdx[num];
				} else if (unitCurIdx[num] == 10) {
					unitStartIdx[num] = unitStartIdx[num] == 2 ? 0 : unitStartIdx[num];
				} else if (unitCurIdx[num] == 15) {
					unitStartIdx[num] = unitStartIdx[num] == 3 ? 0 : unitStartIdx[num];
				}
			}
			sum[num] -= map[dir][unitCurIdx[num]]; // ���ֺ� ������ ����
			unitCurIdx[num] -= cmd[stage]; // �� �̵� ���

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
