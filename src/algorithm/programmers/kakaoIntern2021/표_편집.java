package algorithm.programmers.kakaoIntern2021;

import java.util.Stack;

public class 표_편집 {
	public String solution(int n, int k, String[] cmd) {
        boolean[] map = new boolean[n];
        int index = k;
        Stack<Integer> st = new Stack<>();
        
        for(int z=0;z<cmd.length;z++){
            String[] s = cmd[z].split(" ");
            
            switch(s[0]){
                case "U":
                    int cmdIdx = Integer.parseInt(s[1]);
                    index = moveIndex(index,cmdIdx,-1,map);
                    break;
                case "D":
                    int cmdIdx2 = Integer.parseInt(s[1]);
                    index = moveIndex(index,cmdIdx2,1,map);
                    break;
                case "C":
                    map[index]=true;
                    st.push(index);
                    int pre = moveIndex(index,1,1,map);
                    if(pre==index) index = moveIndex(index,1,-1,map);
                    else index = pre;
                    break;
                case "Z":
                    int popIdx = st.pop();
                    map[popIdx] = false;
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(map[i]?'X':'O');
        }
        
        String answer = sb.toString();
        return answer;
    }
    
    private static int moveIndex(int cur,int idx,int dir, boolean[] map){
        int cnt=0;
        int nidx=cur;
        while(cnt!=idx){
            nidx+=dir; // 1칸 이동
            if(nidx<0||nidx>=map.length){
                nidx-=dir;
                break;
            }
            if(map[nidx]){ // true이면 제거된 행
                continue;
            }
            cnt++;
        }
        return nidx;
    }
    
	public static void main(String[] args) {
		표_편집 c = new 표_편집();
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		System.out.println(c.solution(8, 2, cmd));
	}
}
