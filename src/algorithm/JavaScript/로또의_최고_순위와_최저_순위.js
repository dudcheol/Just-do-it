function solution(lottos, win_nums) {
    let hit=0;
    let zero=0;
    for(let i=0;i<lottos.length;i++){
        const l = lottos[i];
        if(l===0){ // 모르는 숫자
            zero++;
        } else {
            for(let j=0;j<win_nums.length;j++){
                const w = win_nums[j];
                if(l===w){ // 당첨
                    hit++;
                }
            }
        }
    }
    console.log(hit)
    
    var answer = [Math.min(7-hit-zero, 6), Math.min(7-hit, 6)];
    return answer;
}