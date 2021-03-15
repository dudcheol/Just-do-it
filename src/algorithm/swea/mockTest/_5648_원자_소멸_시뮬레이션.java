package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 20:00~ 중간에 5분
public class _5648_원자_소멸_시뮬레이션 {
    private static int N;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static class Dot {
        int x;
        int y;
        int d;
        int k;

        public Dot(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= TC; test_case++) {
            N = Integer.parseInt(br.readLine());
            Queue<Dot> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                q.offer(new Dot(x + 2000, y + 2000, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            int time = 4000;
            int sum = 0;
            Map<Integer, ArrayList<Dot>> map = new HashMap<>();
            while (!q.isEmpty() && time-- > 0) {
                int size = q.size();
                for (int s = 0; s < size; s++) { // q의 사이즈만큼 반복
                    Dot d = q.poll();
                    int nx = d.x + dx[d.d];
                    int ny = d.y + dy[d.d];
                    if (ny<0||nx<0||ny>4000||nx>4000) continue;
                    int key=ny*4000+nx;

                    if (!map.containsKey(key)){
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(new Dot(nx,ny,d.d,d.k));
                }

                // map 확인해서 충돌한 원소 제거
                for(int key : map.keySet()){
                    ArrayList<Dot> list = map.get(key);
                    if (list.size()>1){
                        for (int i = 0; i < list.size(); i++) {
                            sum+=list.get(i).k;
                        }
                    } else {
                        q.offer(list.get(0));
                    }
                }

                map.clear();
            }

            sb.append('#').append(test_case).append(' ').append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
