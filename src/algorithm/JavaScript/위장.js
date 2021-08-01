function solution(clothes) {
  const map = {};
  clothes.forEach((cloth) => {
    if (map[cloth[1]]) {
      map[cloth[1]].push(cloth[0]);
    } else {
      map[cloth[1]] = [cloth[0]];
    }
  });

  var answer = 1;
  for (let key in map) {
    answer *= map[key].length + 1;
  }

  return --answer;
}
