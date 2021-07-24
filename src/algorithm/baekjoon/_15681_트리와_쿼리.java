package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _15681_트리와_쿼리 {
    private static int N,R,Q,cnt;
    private static List[] nodes;
    private static boolean[] visited;
    private static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());// 정점의 수
        R=Integer.parseInt(st.nextToken());// 루트의 번호
        Q=Integer.parseInt(st.nextToken());// 쿼리의 수
        nodes = new List[N+1];
        memo = new int[N+1];
        visited=new boolean[N+1];

        // 정점 초기화
        for (int i = 1; i <= N; i++) {
            nodes[i]=new ArrayList<Integer>();
        }

        // 트리 만들기
        for (int i = 0; i < N-1; i++) {
            st=new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].add(v);
            nodes[v].add(u);
        }

        // *루트부터 시작해서 한번 슥 돌면서 서브트리에 속하는 정점의 수 기록해두기
        countSubtree(R);

        // 쿼리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int U = Integer.parseInt(br.readLine());
            sb.append(memo[U]).append('\n');
        }
        System.out.println(sb);
    }

    private static int countSubtree(int n){
        int ret = 1;
        visited[n]=true;
        List<Integer> node = nodes[n];
        for(int no : node){
            if (visited[no]) continue;
            ret += countSubtree(no);
        }
        return memo[n]=ret;
    }

    // 시간초과 코드
//    private static void dfs(int n, int U, boolean isSub) {
//        visited[n]=true;
//        if (isSub) { // 서브트리에 속함
//            if (memo[n]>0){ // 이미 방문했던 노드여서 기록되어 있음
//                cnt+=memo[n];
//                return;
//            }
//            cnt++;
//        }
//        List<Integer> node = nodes[n];
//        for(int no : node){
//            if (visited[no]) continue;
//            dfs(no, U, no==U?true:isSub);
//        }
//    }
}
