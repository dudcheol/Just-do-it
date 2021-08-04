function solution(bridge_length, weight, truck_weights) {
    
    const b=[];
    let cur=0;
    let time=0;
    while(truck_weights.length>0){
        // 현재 다리에 올라갈 수 트럭 수 확인
        if(b.length < bridge_length){ // 트럭 올라갈 수 있음
            // 현재 트럭이 다리에 올라갔을 때 무게 가능한지 확인
            const truck = truck_weights[0];
            if(cur+truck <= weight){
                // 가능하다면 - 트럭 다리에 올리기
                b.push({truck,time});
                cur += truck; // 현재 다리에 있는 트럭 무게 늘리기
                truck_weights.shift();
            }
        }
        // 다리 위에 트럭이 있다면 다리를 지난 트럭 처리
        if(b.length>0 && time - b[0].time === bridge_length-1){
            cur -= b.shift().truck; // 현재 다리에 있는 트럭 무게 줄이기
        }
        // 시간 늘림
        time++;
    }
    
    // 아직 다리에 차량이 있다면
    while(b.length>0){
        if(time - b[0].time === bridge_length){
            b.shift();
        }
        // 시간 늘림
        time++;
    }
    
    var answer = time;
    return answer;
}