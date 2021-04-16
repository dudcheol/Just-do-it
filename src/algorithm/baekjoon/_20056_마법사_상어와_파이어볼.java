package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 한번 더 풀어보기
// 22:10~23:40 1시간30분
public class _20056_마법사_상어와_파이어볼 {

	private static int N,M,K;
	private static int[] dy = {-1,-1,0,1,1,1,0,-1};
	private static int[] dx = {0,1,1,1,0,-1,-1,-1};
	
	private static class Fire{
		int r,c,m,d,s;

		public Fire(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		Queue<Fire> q = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			q.offer(new Fire(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		ArrayList<Fire>[][] map = null;
		int time=0;
		while(!q.isEmpty()) {
			if(time==K) break;
			
			int size = q.size();
			map = new ArrayList[N+1][N+1];
			
			// 모든 파이어볼 이동시킴
			for (int w = 0; w < size; w++) {
				
				Fire p = q.poll();
				
				// d로 s만큼 이동
				int moveR = dy[p.d]*p.s;
				int moveC = dx[p.d]*p.s;
				
				moveR%=N;
				moveC%=N;
				
				// 한칸씩 이동시킴
				int nr=p.r;
				int nc=p.c;
				moveR = Math.abs(moveR);
				moveC = Math.abs(moveC);
				for (int i = 0; i < moveR; i++) {
					nr+=dy[p.d];
					if(nr<=0) nr=N;
					if(nr>N) nr=1;
				}
				for (int i = 0; i < moveC; i++) {
					nc+=dx[p.d];
					if(nc<=0) nc=N;
					if(nc>N) nc=1;
				}
				
				if(map[nr][nc]==null) {
					map[nr][nc] = new ArrayList();
				}
				map[nr][nc].add(new Fire(nr,nc,p.m,p.s,p.d));
			}
			
			// 이동끝난뒤
			// 2개이상 파이어볼 찾기
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j]==null) continue;
					if(map[i][j].size() >= 2) {
						
						int fsize = map[i][j].size();
						
						// 파이어볼 모두 하나로 합쳐짐
						int nm = 0;
						int ns = 0;
						int Hcnt = 0; //홀의 수
						int Jcnt = 0; //짝의 수
						for (Fire f : map[i][j]) {
							nm += f.m;
							ns += f.s;
							if(f.d%2==0) Jcnt++;
							else Hcnt++;
						}
						
						nm/=5;
						ns/=fsize;
						if(nm==0) continue;
						
						if(Jcnt==fsize || Hcnt==fsize) {
							q.offer(new Fire(i,j,nm,ns,0));
							q.offer(new Fire(i,j,nm,ns,2));
							q.offer(new Fire(i,j,nm,ns,4));
							q.offer(new Fire(i,j,nm,ns,6));
						} else {
							q.offer(new Fire(i,j,nm,ns,1));
							q.offer(new Fire(i,j,nm,ns,3));
							q.offer(new Fire(i,j,nm,ns,5));
							q.offer(new Fire(i,j,nm,ns,7));
						}
						
					} else {
						q.offer(map[i][j].get(0));
					}
				}
			}
			
			time++;
		}
		
		// 남아있는 파이어볼 질량의 합
		int sum=0;
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				if(map[i][j]==null) continue;
//				for(Fire f : map[i][j]) {
//					sum+=f.m;
//				}
//			}
//		}
		while(!q.isEmpty()) {
			sum += q.poll().m;
		}
		
		System.out.println(sum);
	}

}
