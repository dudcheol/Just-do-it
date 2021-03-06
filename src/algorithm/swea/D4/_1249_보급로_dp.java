package algorithm.swea.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _1249_보급로_dp {

	private static final int INF = Integer.MAX_VALUE;
	private static int N;
	private static int[][] map;
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[][] D;
	private static boolean[][] visited;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			D = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j) - '0';
					D[i][j] = INF;
				}
			}

			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
				return Integer.compare(o1[2], o2[2]);
			});
			pq.offer(new int[] { 0, 0, 0 });
			D[0][0] = 0;

			while (!pq.isEmpty()) {
				int[] p = pq.poll();
				int y = p[0];
				int x = p[1];
				int cost = p[2];
				
				if(D[y][x] < cost || visited[y][x])
					continue;
				
				visited[y][x] = true;
				
				if(y==N-1 && x==N-1) {
					answer = cost;
					break;
				}

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx])
						continue;
					
					if(D[ny][nx] > cost + map[ny][nx]) {
						D[ny][nx] = cost + map[ny][nx];
						pq.offer(new int[] { ny, nx, D[ny][nx] });
					}
				}
			}
//			sb.append('#').append(test_case).append(' ').append(D[N-1][N-1]).append('\n');
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
}

/*
#1 2
#2 2
#3 8
#4 57
#5 151
#6 257
#7 18
#8 160
#9 414
#10 395
*/
