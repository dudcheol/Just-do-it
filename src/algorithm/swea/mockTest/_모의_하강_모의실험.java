package algorithm.swea.mockTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _모의_하강_모의실험 {

	private static int N, map[][];
	private static int[] dr = {1,0};//하우
	private static int[] dc = {0,1};//하우

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= 1; test_case++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			simulate();
			print();
			// 우측방향으로 시뮬돌리기위해 배열 회전
			int[][] ret = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ret[i][j]=map[N-j-1][i];
				}
			}
			map=ret;
			print();
			simulate();
			print();
			
			// 정답찾기
			// 맨왼쪽열,맨아래행
			int sum1=0;
			int sum2=0;
			for (int i = 0; i < N; i++) {
				sum1+=map[i][0];
				sum2+=map[N-1][i];
			}
			
			sb.append('#').append(test_case).append(' ').append(sum1).append(' ').append(sum2).append('\n');
		}
		System.out.print(sb);
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

	private static void simulate() {
		loop:for (int order = 0; order < N; order++) {
			if(map[0][order]==0) continue;
			double cur = map[0][order];
			int len = 1;
			for (int i = 1; i < N; i++) {
				int next = map[i][order];
				
				if(next==0) { // 다음칸으로
					cur*=1.9; // 한칸내려갔으므로 힘 1.9배 
					continue;
				}
				
				// 블록이 있다면
				// 다음칸에있는 블록의 저항값을 구한다
				int nlen = getOffValue(i,order);
				
				if(cur > nlen) { // 저항력이긴다면
					// 다음블록과 함께 밑으로 내려감
					len+=nlen; // 합쳐지므로 길이가 길어짐
					cur+=nlen; // 힘도 세짐
					i+=nlen-1; // 합쳐지므로 위치도 더 밑으로 내려가야함
				} else { // 저항력 진다면 멈춘다
					drawBlock(i,order,len);
					continue loop;
				}
			}
			// 끝까지왔으므로 그려줌
			drawBlock(N,order,len);
		}
	}

	private static int getOffValue(int i, int order) {
		int nr=i;
		int len=1;
		while(true) {
			nr+=dr[0];
			if(nr>=N)break;
			if(map[nr][order]==0)break;
			len++;
		}
		return len;
	}

	private static void drawBlock(int i, int order, int len) {
		int[] ret = new int[N];
		// i까지는 이동했으므로 거기까지 이동한 그림을 그림
		int size=len;
		for (int j = i-1; j >= 0; j--) {
			if(size==0)break;
			ret[j]=1;
			size--;
		}
		// i행부턴 그대로 가져옴
		for (int j = i; j < N; j++) {
			ret[j]=map[j][order];
		}
		// 만들어진 블럭을 i열로
		for (int j = 0; j < N; j++) {
			map[j][order] = ret[j];
		}
	}

}
