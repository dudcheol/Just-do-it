let parents;

function solution(n, costs) {
    // union-find를 구현하기위한 parents 배열 생성
    parents = Array.from(Array(n), (_, index) => index);

    costs.sort((o1, o2) => o1[2] - o2[2]);

    let cnt = 0;
    let answer = 0;
    while (costs.length > 0) {
        if (cnt === n - 1) break; // 최소스패닝트리에서 간선의 수는 노드의 수 - 1
        const [start, end, cost] = costs.shift(); // 최소비용 간선 먼저 뽑기
        if (union(start, end)) {
            // 연결됐다면
            cnt++; // 간선 갯수 + 1
            answer += cost; // 연결한 간선 비용 더하기
        }
    }

    return answer;
}

function union(a, b) {
    const aRoot = find(a);
    const bRoot = find(b);
    if (aRoot === bRoot) {
        // 싸이클 생성
        return false;
    }
    parents[bRoot] = aRoot; // b의 루트를 a루트로 설정
    return true;
}

function find(x) {
    if (parents[x] === x) return x;
    return (parents[x] = find(parents[x]));
}
