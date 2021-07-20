package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1991_트리_순회 {

    private static int N;
    private static Node[] nodes;
    private static StringBuilder sb;

    private static class Node{
        char id;
        char left;
        char right;
        Node(char id){
            this.id=id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        nodes = new Node[N];
        // nodes 초기화
        for (int i = 0; i < N; i++) {
            nodes[i]=new Node((char)('A'+i));
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            nodes[node-'A'].left = left;
            nodes[node-'A'].right = right;
        }

        // 전위순회
        sb = new StringBuilder();
        preOrder('A');
        sb.append('\n');
        inOrder('A');
        sb.append('\n');
        postOrder('A');
        System.out.println(sb);
    }

    private static void preOrder(char k) { // 루트 왼 오
        int idx = k-'A';
        char left = nodes[idx].left;
        char right = nodes[idx].right;
        sb.append(k);
        if (left!='.')preOrder(left);
        if (right!='.')preOrder(right);
    }

    private static void inOrder(char k) { // 왼 루트 오
        int idx = k-'A';
        char left = nodes[idx].left;
        char right = nodes[idx].right;
        if (left!='.')inOrder(left);
        sb.append(k);
        if (right!='.')inOrder(right);
    }

    private static void postOrder(char k) { // 왼 오 루트
        int idx = k-'A';
        char left = nodes[idx].left;
        char right = nodes[idx].right;
        if (left!='.')postOrder(left);
        if (right!='.')postOrder(right);
        sb.append(k);
    }
}
