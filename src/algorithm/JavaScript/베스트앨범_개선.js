function solution(genres, plays) {
  const playCnt = {};
  genres.forEach((g, i) => {
    playCnt[g] = playCnt[g] ? playCnt[g] + plays[i] : plays[i];
  });

  const pick = {};
  return genres
    .map((g, i) => {
      return {
        genre: g,
        id: i,
        play: plays[i],
        total: playCnt[genres[i]],
      };
    })
    .sort((o1, o2) => {
      if (o1.total != o2.total) return o2.total - o1.total;
      if (o1.play != o2.play) return o2.play - o1.play;
      return o1.id - o2.id;
    })
    .filter((o, i) => {
      if (pick[o.genre] >= 2) return false;
      pick[o.genre] = pick[o.genre] ? pick[o.genre] + 1 : 1;
      return true;
    })
    .map((o) => o.id);
}
