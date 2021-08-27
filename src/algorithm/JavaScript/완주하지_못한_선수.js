function solution(participant, completion) {
    const map = {};

    let answer = '';

    for (let i = 0; i < participant.length; i++) {
        if (!map[participant[i]]) {
            map[participant[i]] = 1;
        } else {
            map[participant[i]]++;
        }
    }

    for (let i = 0; i < completion.length; i++) {
        map[completion[i]]--;
    }

    for (let i = 0; i < participant.length; i++) {
        if (map[participant[i]] != 0) {
            answer = participant[i];
        }
    }

    return answer;
}
