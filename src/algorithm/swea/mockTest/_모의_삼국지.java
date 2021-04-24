package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _모의_삼국지 {

	private static int N, so[][],by[][],bo[][],bytmp[][],sotmp[][];
	private static int[] dy = {-1,1,0,0}; //상하좌우
	private static int[] dx = {0,0,-1,1}; //상하좌우

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N=Integer.parseInt(br.readLine());
			
			so=new int[N][N];
			by=new int[N][N];
			bo=new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					so[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					by[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					bo[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			simulate();
			
			// 전체병력 수 구하기
			int answer=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer+=by[i][j];
				}
			}
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}

	private static void simulate() {
		int order=1;
		while(true) {
			// 확인
			int[] cnts = gameover();
			int zero=0;
			for (int i = 1; i <= 3; i++) {
				if(cnts[i]==0)zero++;
			}
			if(zero==2) {
				return;
			}
			if(cnts[order]==0) {
				order=order+1==4?1:order+1;
				continue;
			}
			
			// 공격
			bytmp = arraycopy(by);
			sotmp = arraycopy(so);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(so[i][j]!=0 && so[i][j]!=order) { //order가 공격할 수 있는 곳 찾기
						attack(i, j, order);
					}
				}
			}
			by=bytmp;
			so=sotmp;
//			print(by);
//			print(so);
			
			// 지원
			bytmp = arraycopy(by);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(so[i][j]!=0 && so[i][j]==order) {
						supply(i, j, order);
					}
				}
			}
			by=bytmp;
//			print(by);
//			print(so);
			
			// 보충
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					by[i][j]+=bo[i][j];
				}
			}
//			print(by);
//			print(so);
			
			order=order+1==4?1:order+1;
		}
	}

	private static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static int[] gameover() {
		// 1개 나라만 남으면 종료
		int[] cnts = new int[4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnts[so[i][j]]++;
			}
		}
		return cnts;
	}

	private static int[][] arraycopy(int[][] origin) {
		int[][] ret = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(origin[i], 0, ret[i], 0, N);
		}
		return ret;
	}

	private static void supply(int i, int j, int order) {
		int cnt=0;
		int total=0;
		for (int d = 0; d < 4; d++) {
			int ny=i+dy[d];
			int nx=j+dx[d];
			if(isOut(ny,nx) || so[ny][nx]==0) {
				continue;
			}
			if(so[ny][nx]==order) cnt++;
			total++;
		}
		
		if(cnt==total) { //인접한 지역에 다른나라 없음
			// 인접한모든지역에 병력 1/5 지원
			for (int d = 0; d < 4; d++) {
				int ny=i+dy[d];
				int nx=j+dx[d];
				if(isOut(ny,nx) || so[ny][nx]==0) {
					continue;
				}
				bytmp[ny][nx]+=by[i][j]/5;
				bytmp[i][j]-=by[i][j]/5;
			}
		} else { // 5배 초과할때만
			for (int d = 0; d < 4; d++) {
				int ny=i+dy[d];
				int nx=j+dx[d];
				if(isOut(ny,nx) || so[ny][nx]!=order) {
					continue;
				}
				if(by[i][j] > by[ny][nx]*5) {
					bytmp[ny][nx]+=by[i][j]/5;
					bytmp[i][j]-=by[i][j]/5;
				}
			}
		}
	}

	private static void attack(int i, int j, int order) {
		int sum=0; // order번 나라 병력의 합
		for (int d = 0; d < 4; d++) {
			int ny=i+dy[d];
			int nx=j+dx[d];
			if(isOut(ny,nx) || so[ny][nx]!=order) {
				continue;
			}
			sum+=by[ny][nx];
		}
		if(sum > by[i][j]*5) {
			int atsum=0;
			for (int d = 0; d < 4; d++) {
				int ny=i+dy[d];
				int nx=j+dx[d];
				if(isOut(ny,nx) || so[ny][nx]!=order) {
					continue;
				}
				bytmp[ny][nx]-=by[ny][nx]/4; // 1/4만큼 공격
				bytmp[i][j]-=by[ny][nx]/4; // 1/4만큼 공격받음
			}
			sotmp[i][j]=order; // 소유가 바뀐다
			bytmp[i][j]*=-1; // 공격받은만큼이 order의 병력이됨
		}
	}

	private static boolean isOut(int ny, int nx) {
		return ny<0||nx<0||ny>=N||nx>=N;
	}

}





















