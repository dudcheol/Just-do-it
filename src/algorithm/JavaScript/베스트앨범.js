function solution(genres, plays) {
  const playCnt = {};

  // 먼저 실행할 장르 구하기
  for (let i = 0; i < genres.length; i++) {
    if (playCnt[genres[i]]) playCnt[genres[i]] += plays[i];
    else playCnt[genres[i]] = plays[i];
  }

  // 객체 배열 만들기
  const infos = [];
  for (let i = 0; i < genres.length; i++) {
    infos.push({
      genre: genres[i],
      id: i,
      play: plays[i],
      total: playCnt[genres[i]],
    });
  }

  // 생성한 객체 배열 정렬하기
  infos.sort((o1, o2) => {
    if (o1.total != o2.total) {
      return o2.total - o1.total;
    } else {
      if (o1.play != o2.play) {
        return o2.play - o1.play;
      } else {
        return o1.id - o2.id;
      }
    }
  });

  // 장르별로 두 곡씩 모으기
  const pick = {};
  var answer = [];
  infos.forEach((info) => {
    // let p=pick[info.genre];
    if (pick[info.genre]) {
      if (pick[info.genre].length < 2) {
        pick[info.genre].push(info.id);
        answer.push(info.id);
      }
    } else {
      pick[info.genre] = [info.id];
      answer.push(info.id);
    }
  });

  return answer;
}
