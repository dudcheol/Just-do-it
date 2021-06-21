function solution(numbers) {
  var answer = '';

  answer = numbers
    .map((a) => a + '')
    .sort((a, b) => b + a - (a + b))
    .join('');

  return answer[0] === '0' ? '0' : answer;
}
