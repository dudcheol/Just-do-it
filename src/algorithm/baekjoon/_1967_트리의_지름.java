package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class _1967_트리의_지름 {

	private static int N,fnode,max;
	private static boolean[] visited;
	private static List<int[]>[] nodes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); // 노드 수
		nodes = new List[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i]=new ArrayList<>();
		}
		for (int i = 0; i < N-1; i++) {
			st=new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			nodes[p].add(new int[] {c,w});
			nodes[c].add(new int[] {p,w});
		}
		
		// 임의의 노드에서 시작해서 가장 먼 노드 찾기
		visited = new boolean[N+1];
		dfs(1, 0);
		
		// 가장 먼 노드에서 시작해서 가장 먼 노드까지의 길이 구하기 -> 트리의 지름이 됨
		Arrays.fill(visited, false);
		dfs(fnode, 0);
		
		System.out.println(max);
	}

	private static void dfs(int i, int len) {
		if(max < len) {
			max = len;
			fnode = i;
		}
		visited[i]=true;
		List<int[]> node = nodes[i];
		if(node==null)return;
		for(int[] e : node) {
			if(visited[e[0]]) continue;
			dfs(e[0], len+e[1]);
		}
	}
}
