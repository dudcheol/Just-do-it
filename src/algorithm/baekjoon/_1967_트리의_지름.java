package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1967_트리의_지름 {

	private static int N;
	private static Node[] nodes;
	private static boolean[] visited;
	private static int[] memo;
	
	private static class Node{
		List<int[]> list;
		int w;
		
		Node(){
			list = new ArrayList<int[]>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); // 노드 수
		nodes = new Node[N+1];
		visited = new boolean[N+1];
		for (int i = 0; i < N-1; i++) {
			st=new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(nodes[p]==null)nodes[p]=new Node();
			nodes[p].list.add(new int[] {c,w});
		}
		
		// 각 노드별 리프노드까지 갔을 때 최대 길이 구하기
		memo = new int[N+1];
		memo[1] = dfs(1);
		
		System.out.println(Arrays.toString(memo));
			
		int max=0;
		for (int i = 1; i <= N; i++) {
			max=Math.max(max, memo[i]);
		}
		System.out.println(max);
	}

	private static int dfs(int i) {
		visited[i]=true;
		Node node = nodes[i];
		int max=0;
		int sum=0;
		if(node==null)return max;
		for(int[] e : node.list) {
			if(visited[e[0]]) continue;
			int res = dfs(e[0])+e[1];
			max = Math.max(res, max);
			sum+=res;
		}
		memo[i]=sum;
		return max;
	}

}
