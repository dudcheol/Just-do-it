let min = Number.MAX_VALUE;
let visited;

function solution(begin, target, words) {
  visited = new Array(words.length).fill(false);
  dfs(begin, target, words, 0);
  return min === Number.MAX_VALUE ? 0 : min;
}

function dfs(begin, target, words, cnt) {
  if (min < cnt) return;
  if (begin === target) {
    min = Math.min(min, cnt);
    return;
  }
  // 한 글자만 다른 단어 찾기
  for (let i = 0; i < words.length; i++) {
    if (visited[i]) continue;
    let fit = 0;
    for (let j = 0; j < begin.length; j++) {
      if (words[i].charAt(j) === begin.charAt(j)) {
        fit++;
      }
    }
    // 한 글자만 다른 단어 발견하면 그 단어로 바꾸기
    if (fit === begin.length - 1) {
      // console.log(words[i])
      visited[i] = true;
      dfs(words[i], target, words, cnt + 1);
      visited[i] = false;
    }
  }
}
