package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 10:03~11:22
public class _19236_청소년_상어 {

	private static class Fish {
		int no;
		int dir;

		public Fish(int no, int dir) {
			this.no = no;
			this.dir = dir;
		}
	}

	private static int max;
	private static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	private static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 }; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] map = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
			}
		}

		// 상어 0,0 먹음
		max = 0;
		int sr = 0, sc = 0;
		int sd = map[0][0].dir;
		max += map[0][0].no;
		map[0][0] = null;

		dfs(map, sr, sc, sd, max);
		System.out.println(max);
	}

	private static void dfs(Fish[][] map, int r, int c, int d, int sum) {
		max = Math.max(max, sum);
		
		// 물고기 이동
		moveFish(map, r, c);

		// 상어이동
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (isOut(nr, nc)) return;
			if (map[nr][nc] == null) continue; // 물고기없는칸 이동 X
			Fish[][] tmp = arraycopy(map);
			tmp[nr][nc] = null; // 물고기 먹음
			dfs(tmp, nr, nc, map[nr][nc].dir, sum + map[nr][nc].no);
		}
	}

	private static Fish[][] arraycopy(Fish[][] map) {
		Fish[][] ret = new Fish[4][4];
		for (int i = 0; i < 4; i++) { // 복사할 때 객체복사는 이렇게 해야함! System.arraycopy 안됨!
			for (int j = 0; j < 4; j++) {
				if(map[i][j]==null) ret[i][j]=null;
				else ret[i][j]=new Fish(map[i][j].no, map[i][j].dir);
			}
		}
		return ret;
	}

	private static void moveFish(Fish[][] map, int sr, int sc) {
		// 번호 작은 물고기부터 이동
		loop:for (int order = 1; order <= 16; order++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Fish f = map[i][j];
					if (f == null)
						continue;
					if (f.no == order) {
						int nr = i;
						int nc = j;
						int cnt = 0;
						while (true) {
							if (cnt == 8) { // 물고기 이동불가
								break;
							}
							nr = i + dr[f.dir];
							nc = j + dc[f.dir];
							if (isOut(nr, nc) || (sr == nr && sc == nc)) { // 이동불가 상어가 있거나, 공간의 경계를 넘는 칸
								f.dir = (f.dir + 1) % 8;
							} else { // 이동가능
								// 그칸으로이동
								if (map[nr][nc] != null) { // 이미 물고기가 있다면 위치 스왑
									Fish tmp = map[i][j]; // << 가능
									map[i][j] = map[nr][nc];
									map[nr][nc] = tmp;
								} else {
									map[nr][nc] = map[i][j];
									map[i][j] = null;
								}
								break;
							}
							cnt++;
						}
						continue loop;
					}
				}
			}
		}
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= 4 || nc >= 4;
	}

}
