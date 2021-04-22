package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간 25분
public class _20061_모노미노도미노_2 {

	private static int N, map[][], score, cnt;
	private static int[] dr = {0,1}; //우,하
	private static int[] dc = {1,0}; //우,하

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		// 도미노 판 만들기
		score=cnt=0;
		map = new int[10][10];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			moveBlock(t,x,y);
			clearLine();
			clearMidLine();
		}
		
		// 초록색,파란색 보드 타일 수 세기
		for (int i = 6; i <= 9; i++) {
			for (int j = 0; j <= 3; j++) {
				if(map[i][j]==1)cnt++;
				if(map[j][i]==1)cnt++;
			}
		}
		
		System.out.println(score);
		System.out.println(cnt);
	}

	private static void clearMidLine() {
		// 파,초의 미드에 블록이 있는지 확인
		int bcnt=0;
		int gcnt=0;
		for (int i = 4; i <= 5; i++) {
			int bmcnt=0;
			int gmcnt=0;
			for (int j = 0; j <= 3; j++) {
				if(map[j][i]==1)bmcnt++;
				if(map[i][j]==1)gmcnt++;
			}
			if(bmcnt>0) { // 블록존재하는 열
				bcnt++;
			}
			if(gmcnt>0) { // 블록존재하는 행
				gcnt++;
			}
		}
		if(bcnt==1) { // 사라져야하는 열
			removeCol(9);
		} else if(bcnt==2) {
			removeCol(8);
			removeCol(9);
		}
		if(gcnt==1) { // 사라져야하는 행
			removeRow(9);
		} else if(gcnt==2) {
			removeRow(8);
			removeRow(9);
		}
	}

	private static void clearLine() {
		// 파,초 가득찬 '열' 확인
		for (int i = 6; i <= 9; i++) {
			int bcnt=0;
			int gcnt=0;
			for (int j = 0; j <= 3; j++) {
				if(map[j][i]==1)bcnt++;
				if(map[i][j]==1)gcnt++;
			}
			if(bcnt==4) { // 사라져야하는 열
				removeCol(i);
				score++;
			}
			if(gcnt==4) { // 사라져야하는 행
				removeRow(i);
				score++;
			}
		}
	}

	private static void removeRow(int c) {
		for (int i = c; i >= 4; i--) { // 4까지 커버해줘야함
			map[i][0]=map[i-1][0];
			map[i][1]=map[i-1][1];
			map[i][2]=map[i-1][2];
			map[i][3]=map[i-1][3];
		}
	}

	private static void removeCol(int c) {
		for (int i = c; i >= 4; i--) { // 4까지 커버해줘야함
			map[0][i]=map[0][i-1];
			map[1][i]=map[1][i-1];
			map[2][i]=map[2][i-1];
			map[3][i]=map[3][i-1];
		}
	}

	private static void moveBlock(int t, int x, int y) {
		switch(t) {
		case 1:
			moveOneByOne(x,y,1); //하
			moveOneByOne(x,y,0); //우
			break;
		case 2:
			moveOneByTwo(x,y,1);
			moveOneByTwo(x,y,0);
			break;
		case 3:
			moveTwoByOne(x,y,1);
			moveTwoByOne(x,y,0);
			break;
		}
	}

	private static void moveTwoByOne(int x, int y, int d) {
		int nr=x;
		int nc=y;
		int nrr=x+1;
		int ncc=y;
		while(true) {
			nr+=dr[d];
			nc+=dc[d];
			nrr+=dr[d];
			ncc+=dc[d];
			if(isOut(nr,nc)||isOut(nrr,ncc)) { // 범위벗어났으므로 그 전에 위치시키면됨
				nr-=dr[d];
				nc-=dc[d];
				nrr-=dr[d];
				ncc-=dc[d];
				map[nr][nc]=1;
				map[nrr][ncc]=1;
				return;
			}
			if(map[nr][nc]!=0 || map[nrr][ncc]!=0) { // 이 위치에 다른 블록이 있다면 그 전에 위치시키면 됨
				nr-=dr[d];
				nc-=dc[d];
				nrr-=dr[d];
				ncc-=dc[d];
				map[nr][nc]=1;
				map[nrr][ncc]=1;
				return;
			}
		}
	}

	private static void moveOneByTwo(int x, int y, int d) {
		int nr=x;
		int nc=y;
		int nrr=x;
		int ncc=y+1;
		while(true) {
			nr+=dr[d];
			nc+=dc[d];
			nrr+=dr[d];
			ncc+=dc[d];
			if(isOut(nr,nc)||isOut(nrr,ncc)) { // 범위벗어났으므로 그 전에 위치시키면됨
				nr-=dr[d];
				nc-=dc[d];
				nrr-=dr[d];
				ncc-=dc[d];
				map[nr][nc]=1;
				map[nrr][ncc]=1;
				return;
			}
			if(map[nr][nc]!=0 || map[nrr][ncc]!=0) { // 이 위치에 다른 블록이 있다면 그 전에 위치시키면 됨
				nr-=dr[d];
				nc-=dc[d];
				nrr-=dr[d];
				ncc-=dc[d];
				map[nr][nc]=1;
				map[nrr][ncc]=1;
				return;
			}
		}
	}

	// 1x1 오른쪽으로 놓일 수 있는 위치 찾기
	private static void moveOneByOne(int x, int y, int d) {
		int nr=x;
		int nc=y;
		while(true) {
			nr+=dr[d];
			nc+=dc[d];
			if(isOut(nr,nc)) { // 범위벗어났으므로 그 전에 위치시키면됨
				nr-=dr[d];
				nc-=dc[d];
				map[nr][nc]=1;
				return;
			}
			if(map[nr][nc]!=0) { // 이 위치에 다른 블록이 있다면 그 전에 위치시키면 됨
				nr-=dr[d];
				nc-=dc[d];
				map[nr][nc]=1;
				return;
			}
		}
	}

	private static boolean isOut(int x, int y) {
		return x<0||y<0||x>=10||y>=10;
	}

}
