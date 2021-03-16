package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_모의_요리사 {
	private static int N,map[][],a[],b[],selected[], min;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = Integer.parseInt(br.readLine());
			map=new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st =  new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			a=new int[N/2];
			b=new int[N/2];
			visited = new boolean[N];
			selected = new int[2];
			min = Integer.MAX_VALUE;
			// 조합으로 N/2개 뽑기
			comb(0, 0);
			
			sb.append('#').append(test_case).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}

	private static void comb(int k, int idx) {
		if(k==N/2) {
			// 선택된 애들로 시너지 구하기
			int aidx=0,bidx=0;
			for (int i = 0; i < N; i++) {
				if(visited[i]) a[aidx++]=i;
				else b[bidx++]=i;
			}
			int ares = 0;
			int bres = 0;
			// 시너지 구하기
			for (int i = 0; i < N/2; i++) {
				for (int j = i+1; j < N/2; j++) {
					ares+=map[a[i]][a[j]];
					ares+=map[a[j]][a[i]];
					bres+=map[b[i]][b[j]];
					bres+=map[b[j]][b[i]];
				}
			}
			
			min = Math.min(min, Math.abs(ares-bres));
			return;
		}
		for (int i = idx; i < N; i++) {
			visited[i] = true;
			comb(k+1, i+1);
			visited[i] = false;
		}
	}

}
