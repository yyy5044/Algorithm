import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {       
        Deque<int[]> trucks = new ArrayDeque<>();
        for (int i = 0; i < truck_weights.length; i++) {
            trucks.add(new int[]{bridge_length, truck_weights[i]});
        }
        
        Deque<int[]> bridge = new ArrayDeque<>();
        int sum = 0;
        int time = 0;
        while(!trucks.isEmpty()) {
            int[] cur = trucks.poll();
            
            if (!bridge.isEmpty()) { // 다리에 트럭 있으면
                for (int i = 0; i < bridge.size(); i++) { // 지나간 만큼 차감
                    int[] tmp = bridge.poll();
                    bridge.add(new int[]{tmp[0]-1, tmp[1]}); 
                }    
            }
            
            if (bridge.peek() != null && bridge.peek()[0] <= 0) {
                int[] tmp = bridge.poll(); // 다 지나간 트럭 버리기
                sum -= tmp[1]; // 지나갔으니 무게 빼기
            }
            
            if (sum + cur[1] <= weight && bridge.size() <= bridge_length) {
                // 다리에 탈 수 있는지 확인
                bridge.add(cur); // 탑승
                sum += cur[1]; // 탑승했으면 무게 추가
            } else {
                trucks.addFirst(cur); // 못탔으면 다시 앞에 넣기
            }
            
            time++;
        }
        
        return bridge_length + time;
    }
}