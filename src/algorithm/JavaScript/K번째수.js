function solution(array, commands) {
  var answer = [];
  for (let cmd of commands) {
    const i = cmd[0] - 1;
    const j = cmd[1];
    const k = cmd[2] - 1;
    const res = array.slice(i, j);
    res.sort((a, b) => a - b); // 그냥 사용하면 문자열 비교가 되어버림
    answer.push(res[k]);
  }
  return answer;
}
