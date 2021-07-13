package algorithm.programmers.kakaoIntern2021;

import java.util.*;

public class 거리두기_확인하기 {
	
	private static int[] dy = {-1,1,0,0};
    private static int[] dx = {0,0,-1,1};
    
    public int[] solution(String[][] places) {
        List<Integer> list = new ArrayList<>();
        loop:for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(places[i][j].charAt(k)=='P'){
                        if(!bfs(j,k,places[i])) {
                            list.add(0);
                            continue loop;
                        }
                    }   
                }
            }
            list.add(1);
        }
        
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
    
    private static boolean bfs(int y,int x, String[] map){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y,x,0});
        boolean[][] visited = new boolean[5][5];
        visited[y][x]=true;
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            for(int d=0;d<4;d++){
                int ny=p[0]+dy[d];
                int nx=p[1]+dx[d];
                if(ny<0||nx<0||ny>=5||nx>=5||map[ny].charAt(nx)=='X'||visited[ny][nx]) continue;
                if(p[2]>=2) continue;
                if(map[ny].charAt(nx)=='P') {
                    // System.out.println(y+","+x+"에서");
                    // System.out.print(ny+",");
                    // System.out.println(nx);
                    return false;
                }
                q.offer(new int[]{ny,nx,p[2]+1});
                visited[ny][nx]=true;
            }
        }
        
        return true;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
