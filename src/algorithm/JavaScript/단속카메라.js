function solution(routes) {
    // 먼저 끝나는 순서로 정렬
    let endorder = routes
        .map((route) => ({ s: route[0], e: route[1], done: false }))
        .sort((o1, o2) => o1.e - o2.e);

    let cnt = 0;
    while (endorder.length > 0) {
        const poll = endorder.shift();

        cnt++; // 현재 끝점에 설치

        // 현재 끝점보다 빠르게 출발했던 차량 전부 방문처리 (감속카메라를 만나므로)
        endorder.forEach((route) => {
            if (poll.e >= route.s) route.done = true;
        });

        // 감속카메라를 만난 차량 제거
        endorder = endorder.filter((route) => !route.done);
    }

    return cnt;
}
