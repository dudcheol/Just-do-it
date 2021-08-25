function solution(brown, yellow) {
    const num = brown + yellow;

    // yellow를 기준으로 최대공약수 구하기
    let divider = 1,
        target = yellow;
    while (true) {
        const mok = target / divider;
        if (divider > mok) break;

        if (target % divider === 0) {
            // 나누어떨어지면 공약수가됨
            if ((divider + 2) * (mok + 2) === num) {
                return [mok + 2, divider + 2];
            }
        }

        divider++;
    }

    return [];
}
