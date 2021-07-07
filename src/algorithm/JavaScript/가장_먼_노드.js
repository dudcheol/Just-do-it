let nodes;
let visited;

function solution(n, edge) {
    var answer = 0;
    
    //노드만들기
    let num=1;
    nodes = new Array(n+1).fill(null).map(()=>new Array());
    visited = new Array(n+1).fill(false);
    for(let i=0;i<edge.length;i++){
        const start = edge[i][0];
        const end = edge[i][1];
        nodes[start].push(end);
        nodes[end].push(start);
    }

    // 큐 초기화
    const q = [];
    visited[1]=true;
    q.push(1);
    
    let cnt=0;
    while(q.length>0){
        const size = q.length;
        cnt=size;
        for(let i=0;i<size;i++){
            const p = q.shift();
            for(let j=0;j<nodes[p].length;j++){
                const target = nodes[p][j];
                if(visited[target]) continue;
                q.push(target);
                cnt++;
                visited[target]=true;
            }
        }
    }
    
    return cnt;
}