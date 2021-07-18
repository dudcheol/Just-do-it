package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _11725_트리의_부모_찾기 {

    private static int N;
    private static List[] nodes;
    private static boolean[] visited;
    private static int[] parents;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N=Integer.parseInt(br.readLine());
        nodes = new List[N+1];

        // 노드에 리스트 생성
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }

        visited = new boolean[N+1];
        parents = new int[N+1]; // 부모노드 기록

        // 1노드부터 순회
        dfs(1);

//        System.out.println(Arrays.toString(parents));
        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }

    }

    private static void dfs(int k) {
        visited[k]=true;
        List<Integer> node = nodes[k];
        int size = node.size();
        for (int i = 0; i < size; i++) {
            int target = node.get(i);
            if (visited[target]) continue; // 이미 방문한 노드 제외
            parents[target]=k; // 타겟 노드의 부모는 k가 됨
            dfs(target); // 다음 노드로 이동
        }
    }
}
