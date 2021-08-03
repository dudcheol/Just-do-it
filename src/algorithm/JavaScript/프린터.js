function solution(priorities, location) {
    const q = priorities.map((p, i)=>{
        return {p, loc:i};
    });
    
    let cnt=1;
    while(priorities.length > 0){
        // 현재 대기열 가장 앞에있는 문서 확인
        const doc = q.shift();
        
        // 나머지 문서 중에 높은 문서가 있는지 찾음
        // let isOk=true;
        // for(let i=0;i<q.length;i++){
        //     if(q[i].p > doc.p){
        //         isOk=false;
        //         break;
        //     }
        // }

        // Array.prototype.some()
        // some() 메서드는 배열 안의 어떤 요소라도 주어진 판별 함수를 통과하는지 테스트합니다.
        // 참고: 빈 배열에서 호출하면 무조건 false를 반환합니다.
        let isOk = q.some(e => e.p > doc.p); // q에 있는 요소의 p 중 doc.p보다 큰 수가 있다면 true 없다면 false 
        
        if(!isOk){ // 존재하지 않는다면 현재 문서 꺼내기 그대로 진행
            if(doc.loc === location){
                break;
            } else cnt++;
        } else { // 존재한다면 다시 집어넣음
            q.push(doc);
        }
    }

    var answer = cnt;
    return answer;
}