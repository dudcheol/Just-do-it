package algorithm.baekjoon;

import java.util.*;

public class _6416_트리인가 {
    private static Map<Integer, List> uv;
    private static Set<Integer> set;

    /*
    * 트리의 성질
    * 1. 루트 노드는 단 하나
    * 2. 정점의 수 - 간선의 수 = 1 (루트 노드를 제외한 모든 노드는 반드시 단 하나의 들어오는 간선이 존재한다.)
    * 3. 한 노드에 들어오는 간선의 갯수는 반드시 하나 (루트에서 다른 노드로 가는 경로는 반드시 가능하며, 유일하다. 이는 루트를 제외한 모든 노드에 성립해야 한다.)
    * 4. 정점이 없어도 트리다
    * */

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int u = 0,v = 0;
        int caseno=1;
        boolean isTree = true;
        uv=new HashMap<>();
        set= new HashSet<>();
        int edgecnt = 0;
        while (true){
            u = sc.nextInt();
            v = sc.nextInt();

            if (u==-1 && v==-1) break;

            if (u==0 && v==0){ // 테스트케이스 끝
                if (set.size()==0){ // 정점이 없는 것도 트리다
                    sb.append("Case ").append(caseno++).append(" is a tree.").append('\n');
                    continue;
                }

                // 루트노드가 하나인가
                // u->v로 가는 노드 중 set에 없는 노드가 루트노드임(들어오는 간선이 하나도 없는 노드)
                int rootcnt=0;
                for(Map.Entry<Integer, List> e : uv.entrySet()){
                    if (!set.contains(e.getKey())){
                        rootcnt++;
                    }
                }
                isTree = isTree ? rootcnt == 1 : false;

                // 정점 갯수 - 간선 갯수 == 1 인가
                isTree = isTree ? set.size()+1 - edgecnt == 1 : false;

                sb.append("Case ").append(caseno++).append(isTree?" is a tree.":" is not a tree.").append('\n');

                isTree=true;
                uv.clear();
                set.clear();
                edgecnt=0;
                continue;
            }

            if (!isTree) continue;

            // u->v 간선
            if (uv.containsKey(u)){
                uv.get(u).add(v);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(v);
                uv.put(u, list);
            }
            edgecnt++; // 간선의 갯수 세기

            // 들어오는 간선 확인
            if (set.contains(v)){ // 이미 존재한다면 "반드시 단 하나의 들어오는 간선이 존재한다"를 위반함
                isTree=false;
                continue;
            }
            set.add(v);

        }
        System.out.println(sb);
    }

//    private static boolean isAllVisit(int root) {
//        Queue<Integer> q = new LinkedList<>();
//        Set<Integer> visited = new HashSet<>();
//        q.offer(root);
//        visited.add(root);
//        while (!q.isEmpty()){
//            int node = q.poll();
//            List<Integer> list = uv.get(node);
//            if (list==null) continue;
//            for(int l : list){
//                if (visited.contains(l)) continue; // 이미 방문했던 노드
//                visited.add(l);
//                q.offer(l);
//            }
//        }
//        return set.size()+1 == visited.size();
//    }
}
