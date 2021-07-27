package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _17073_나무_위의_빗물 {

	private static int N,W,lcnt;
	private static List[] nodes;
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		nodes=new List[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i]=new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {
			st=new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			nodes[u].add(v);
			nodes[v].add(u);
		}
		// 리프노드 찾기
		visited=new boolean[N+1];
		dfs(1);
		System.out.println(W/(double)lcnt);
	}

	private static void dfs(int i) {
		visited[i]=true;
		List<Integer> node = nodes[i];
		int cnt=0;
		for(int n : node) {
			if(visited[n])continue;
			dfs(n);
			cnt++;
		}
		if(cnt==0) { // 자식노드가 없다면
			lcnt++;
		}
	}

}
