package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _5639_이진_검색_트리 {
	private static StringBuilder sb;
	
	private static class Node{
		Node p;
		Node l;
		Node r;
		int num;
		public Node(Node p, Node l, Node r, int num) {
			this.p = p;
			this.l = l;
			this.r = r;
			this.num = num;
		}
		private void insert(int num) {
			if(num < this.num) { //왼쪽 서브트리
				if(l==null) l=new Node(this, null, null, num);
				else l.insert(num);
			} else {
				if(r==null) r=new Node(this, null, null, num);
				else r.insert(num);
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String input;
		// 루트노드
		int rootNum=Integer.parseInt(br.readLine());
		Node root = new Node(null, null, null, rootNum);
		while((input=br.readLine())!=null) {
			int cur = Integer.parseInt(input);
			// 트리 만들기
			root.insert(cur);
		}
		postOrder(root);
		System.out.println(sb);
	}

	private static void postOrder(Node node) {
		if(node==null) return;
		postOrder(node.l);
		postOrder(node.r);
		sb.append(node.num).append('\n');
	}

}
