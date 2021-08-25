let visited;
let number;
const set = new Set();

function solution(numbers) {
    // numbers로 만들 수 있는 수 모두 탐색 - ㄱ
    // 순열 사용?
    number = numbers;
    visited = Array.from(new Array(number.length), (x) => false);
    perm(0, '');
    const nums = [...set].filter((e) => e > 1);

    // ㄱ을 가지고 소수인지 판별
    let answer = 0;
    nums.forEach((num) => {
        if (isSosu(num)) {
            // 소수라면 cnt + 1
            answer++;
        }
    });

    return answer;
}

function perm(k, res) {
    set.add(Number(res));
    // const _res = parseInt(res);
    // !isNaN(_res) && set.add(parseInt(res));
    if (k === number.length) {
        return;
    }
    for (let i = 0; i < number.length; i++) {
        if (visited[i]) continue;
        visited[i] = true;
        perm(k + 1, res + number[i]);
        visited[i] = false;
    }
}

function isSosu(num) {
    let i = 2;
    // while (i < num) {
    while (i <= Math.sqrt(num)) {
        if (num % i === 0) return false;
        i++;
    }
    return true;
}
