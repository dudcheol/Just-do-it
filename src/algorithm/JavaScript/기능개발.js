function solution(progresses, speeds) {
  // 스택 초기화
  const st = [];
  progresses.reverse().forEach((p, i) => {
    st.push({ p, s: speeds[speeds.length - 1 - i] });
  });

  const ret = {};
  let time = 0;
  let cnt = 0;
  while (st.length > 0) {
    let { p, s } = st.pop();

    // console.log('pop',p,s);
    // console.log(p+time*s);

    // 현재 흐른 시간을 적용하고, 배포가 가능한지 판단한다
    if (p + s * time >= 100) {
      // 배포 가능하다면 다음으로 넘김
      cnt++;
      ret[time] = ret[time] ? cnt : 1;
      continue;
    }

    // 배포 불가능하다면 하루 늘린다
    time++;
    cnt = 0;
    st.push({ p, s }); // 배포하지 못했으므로 다시 스택에 넣음
    // console.log('push',p,s);
  }

  var answer = [];
  for (let l in ret) {
    answer.push(ret[l]);
  }
  return answer;
}
