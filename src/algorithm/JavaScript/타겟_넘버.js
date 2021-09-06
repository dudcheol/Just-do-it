let answer;

function solution(numbers, target) {
    answer = 0;
    recursion(0, 0, numbers, target);

    return answer;
}

function recursion(k, val, numbers, target) {
    if (k === numbers.length) {
        if (target === val) {
            answer++;
        }
        return;
    }
    recursion(k + 1, val + numbers[k], numbers, target);
    recursion(k + 1, val - numbers[k], numbers, target);
}
