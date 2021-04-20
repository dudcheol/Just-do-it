package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _20058_마법사_상어와_파이어스톰_다시풀기 {

	private static int N, Q, map[][], cmd[], size;
	private static int[] dy = { 0, 1, 0, -1, 0 }; // 우하좌상우
	private static int[] dx = { 1, 0, -1, 0, 1 }; // 우하좌상우
	private static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	private static int[] dc = { 0, 0, -1, 1 }; // 상하좌우
	private static boolean[][] visited;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		size = 1 << N;
		map = new int[size][size];
		cmd = new int[Q];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}

		// 단계별 시뮬레이션 진행
		for (int q = 0; q < Q; q++) {
			int l = cmd[q];

			int[][] tmp = arraycopy(map);

			// 2^l로 격자 나누기
			int divSize = 1 << l;
			int cnt = size / divSize;

			for (int h = 0; h < cnt; h++) {

				for (int w = 0; w < cnt; w++) {

					// 회전할 시작지점 찾기
					int sy = divSize * h;
					int sx = divSize * w;

					// 시작지점에서부터 회전돌리기
					int len = divSize;
					int k=0;
					
					while (len>0) { // 크기를 줄여가면서 l번 시도해야함
//						int len = (int)Math.pow(2, l-k);
//						int len = 1 << (l - k);
						int py = sy + k;
						int px = sx + k;

						int[] memo = new int[len];
						for (int d = 0; d < 4; d++) { // 5방향 봄
							for (int i = 0; i < len; i++) {
//								tmp[ny][nx] = map[py][px];
								memo[i] = map[py][px];
								py += dy[d];
								px += dx[d];
							}

							// 한번 더 가있으므로 한번 빼줌
							py -= dy[d];
							px -= dx[d];
							int ny = py;
							int nx = px;

							// 여기서부터 시작하면 됨
							for (int i = 0; i < len; i++) {
								tmp[ny][nx] = memo[i];
								ny += dy[d + 1];
								nx += dx[d + 1];
							}

						}
						
						len -= 2;
						k++;
					}

				}

			}
			
//			System.out.println("----tmp----" + l);
//			print(tmp);

			int[][] melt = new int[size][size];

			// 줄어들 얼음 찾고 줄이기
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(tmp[i][j]==0) continue;
					int non = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr < 0 || nc < 0 || nr >= size || nc >= size)
							continue;
						if (tmp[nr][nc] != 0)
							non++;
					}
					if (non >= 3) {
						melt[i][j] = tmp[i][j];
					} else {
						melt[i][j] = tmp[i][j] - 1 < 0 ? 0 : tmp[i][j] - 1;
					}
//					melt[i][j] = non>=3 ? tmp[i][j]:tmp[i][j]-1;
				}
			}
			
//			System.out.println("----melt----" + q);
//			print(melt);

			map = melt; // 결과반영
		}

		// 남아있는 얼음의 합
		int sum = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += map[i][j];
			}
		}

		// 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
		visited = new boolean[size][size];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] != 0)
					max = Math.max(max, dfs(i, j));
			}
		}

		System.out.println(sum);
		System.out.println(max == Integer.MIN_VALUE ? 0 : max);

	}

	private static void print(int[][] tmp) {
		for (int i = 0; i < size; i++) {
			System.out.println(Arrays.toString(tmp[i]));
		}
	}

	private static int[][] arraycopy(int[][] origin) {
		int[][] ret = new int[size][size];
		for (int i = 0; i < size; i++) {
			System.arraycopy(origin[i], 0, ret[i], 0, size);
		}
		return ret;
	}

	private static int dfs(int i, int j) {
		int ret = 1;
		visited[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (nr < 0 || nc < 0 || nr >= size || nc >= size || visited[nr][nc])
				continue;
			if (map[nr][nc] != 0) {
				ret += dfs(nr, nc);
			}
		}
		return ret;
	}

}
