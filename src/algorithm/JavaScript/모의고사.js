function solution(answers) {
    const one = [1, 2, 3, 4, 5];
    const two = [2, 1, 2, 3, 2, 4, 2, 5];
    const three = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];

    const info = [
        { id: 1, value: 0 },
        { id: 2, value: 0 },
        { id: 3, value: 0 },
    ];
    answers.forEach((answer, i) => {
        if (answer === one[i % 5]) info[0].value++;
        if (answer === two[i % 8]) info[1].value++;
        if (answer === three[i % 10]) info[2].value++;
    });

    info.sort((o1, o2) => o2.value - o1.value);

    const max = info[0].value;
    const answer = info.filter((e) => e.value === max).map((e) => e.id);

    return answer;
}
