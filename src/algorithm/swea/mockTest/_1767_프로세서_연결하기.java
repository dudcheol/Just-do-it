package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1767_프로세서_연결하기 {
    private static int N, coreCnt, cores[];
    private static int[][] map;
    private static ArrayList<int[]> list;
    private static int[] dy = { -1, 1, 0, 0 };
    private static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            list = new ArrayList<int[]>();
            coreCnt = Integer.MIN_VALUE;
            cores = new int[13];
            Arrays.fill(cores, Integer.MAX_VALUE);
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if(!(i==0 || j==0 || i==N-1 || j==N-1)) list.add(new int[] { i, j });
                    }
                }
            }

            dfs(0, 0, map);

            sb.append('#').append(test_case).append(' ').append(cores[coreCnt]).append('\n');
        }

        System.out.print(sb);

    }

    private static void dfs(int k, int core, int[][] map) {
        if (k == list.size()) {
            if (coreCnt <= core) {
                coreCnt = core;
                int cnt = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == 2)
                            cnt++;
                    }
                }
                cores[coreCnt] = Math.min(cores[coreCnt], cnt);
            }
            return;
        }

        int y = list.get(k)[0];
        int x = list.get(k)[1];

        for (int d = 0; d < 4; d++) {
            int ny = y;
            int nx = x;
            int[][] tmp = copymap(map);
            while (true) {
                ny += dy[d];
                nx += dx[d];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    dfs(k + 1, core + 1, tmp);
                    break;
                }
                if (tmp[ny][nx] != 0) {
                    break;
                }
                tmp[ny][nx] = 2;
            }
        }
        dfs(k + 1, core, map);
    }

    private static int[][] copymap(int[][] origin) {
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(origin[i], 0, ret[i], 0, N);
        }
        return ret;
    }
}
