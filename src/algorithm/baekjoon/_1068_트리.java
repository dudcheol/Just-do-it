package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 19:30~
public class _1068_트리 {

	private static int N, cnt;
	private static List[] nodes;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		nodes = new List[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new ArrayList<>();
		}
		int rootnode = 0;
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent!=-1) { // 루트가 아니면
				nodes[parent].add(i); // 부모에서 자식 연결
			} else {
				rootnode = i;
			}
		}
		
		int delnode = Integer.parseInt(br.readLine());
		nodes[delnode]=null;
		
		// 리프노드  찾기 (자식의 개수가 0인 노드 찾기)
		Queue<Integer> q = new LinkedList<>();
		q.offer(rootnode);
		visited = new boolean[N];
		
		// 아래 풀이는
		//   0
		// 1 2 3
		//   4
		// 에서 4를 제거했을 때 리프노드가 3개가 되어야 하는데 2를 답으로 낼 것이다
		while(!q.isEmpty()) {
			int p = q.poll();
			List<Integer> node = nodes[p];
			
			if(node==null) continue;
//			if(node.size()==0) { // 확실해?
//				cnt++;
//				continue;
//			}
			
			int ncnt=0;
			for(int n : node) {
				if(visited[n]||n==delnode) continue;
//				if(n!=delnode)ncnt++;
				ncnt++;
				visited[n]=true;
				q.offer(n);
			}
			if(ncnt==0) cnt++;
		}
		
		System.out.println(cnt); // 지워진 노드 제거
	}

}
