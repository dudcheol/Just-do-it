package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 10:25~11:20
public class _20057_마법사_상어와_토네이도_다시풀기 {

	private static int N, map[][], y, x, sum;
	private static int[] dy = { 0, 1, 0, -1 };// 좌하우상
	private static int[] dx = { -1, 0, 1, 0 };// 좌하우상
	private static double[][] rotate = {
			{ 0, 0, 0.02, 0, 0 }, 
			{ 0, 0.1, 0.07, 0.01, 0 }, 
			{ 0.05, -1, 0, 0, 0 },
			{ 0, 0.1, 0.07, 0.01, 0 }, 
			{ 0, 0, 0.02, 0, 0 }, 
		};
	private static double[][][] vals;
//	private static double[][][] vals = { 
//			{
//				{ 0, 0, 0.02, 0, 0 }, 
//				{ 0, 0.1, 0.07, 0.01, 0 }, 
//				{ 0.05, -1, 0, 0, 0 },
//				{ 0, 0.1, 0.07, 0.01, 0 }, 
//				{ 0, 0, 0.02, 0, 0 }, 
//			},
//			{
//				{0,0,0,0,0},
//				{0,0.01,0,0.01,0},
//				{0.02,0.07,0,0.07,0.02},
//				{0,0.1,-1,0.1,0},
//				{0,0,0.05,0,0},
//			},
//			{
//				{0,0,0.02,0,0},
//				{0,0.01,0.07,0.1,0},
//				{0,0,0,-1,0.05},
//				{0,0.01,0.07,0.1,0},
//				{0,0,0.02,0,0},
//			},
//			{
//				{0,0,0.05,0,0},
//				{0,0.1,-1,0.1,0},
//				{0.02,0.07,0,0.07,0.02},
//				{0,0.01,0,0.01,0},
//				{0,0,0,0,0},
//			},
//	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotateVal();

		// 시작점
		y = x = N / 2;

		// 달팽이 방향 이동
		int d = 0;
		int move = 0;
		sum = 0;
		loop: while (true) {
			move++;
			for (int cnt = 0; cnt < 2; cnt++) {
				for (int m = 0; m < move; m++) {
					if (y == 0 && x == 0)
						break loop;
					y += dy[d];
					x += dx[d];

					// 시뮬돌리기
					simulate(d);
				}
				d = (d + 1) % 4;
			}
		}

		System.out.println(sum);
	}

	private static void rotateVal() {
		vals = new double[4][][];
		
		vals[0]=rotate;
		
		for (int k = 1; k < 4; k++) {
			
			double[][] tmp = new double[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tmp[i][j] = vals[k-1][j][4-i];
				}
			}
			
			vals[k] = tmp;
		}
	}

	private static void simulate(int d) {
		int send = map[y][x];
		double[][] val = vals[d];

		int sy = y - 2;
		int sx = x - 2;
		// ny,nx에 있는 모래가 val로 흩뿌려진다
		int ay = 0;
		int ax = 0;
		int tmpsum = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (val[i][j] == 0)
					continue;

				int ny = sy + i;
				int nx = sx + j;

				if (val[i][j] == -1) { // a위치 기록
					ay = ny;
					ax = nx;
					continue;
				}

				int rest = (int) (send * val[i][j]);
				tmpsum += rest; // 흩뿌려진 모래양 누적

				if (ny < 0 || nx < 0 || ny >= N || nx >= N) { // 밖으로 나간 모래
					sum += rest;
					continue;
				}
				map[ny][nx] += rest; // 흩날린 모래 그 위치에 더해짐
			}
		}
		if (ay < 0 || ax < 0 || ay >= N || ax >= N) { // a가 격자밖으로 나감
			sum += send - tmpsum;
		} else {
			map[ay][ax] += send - tmpsum;
		}
		map[y][x] = 0;
	}
}
