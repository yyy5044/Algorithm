import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> trucks = new ArrayDeque<>();
        Deque<int[]> bridge = new ArrayDeque<>();
        
        for (int t : truck_weights) {
            trucks.add(t);
        }
        
        int sum = 0;
        int time = 0;
        while (!trucks.isEmpty()) {
            if (!bridge.isEmpty() && time >= bridge.peek()[0] + bridge_length) { // 다리가 안 비어있고, 다 지나갔다면
                int[] tmp = bridge.poll();
                sum -= tmp[1]; // 무게 차감
            }
            
            if (sum + trucks.peek() <= weight) { // 다리에 올라가도 괜찮다면
                int tmp = trucks.poll(); // 트럭 꺼내서
                bridge.add(new int[]{time, tmp}); // 다리 위에 올리기
                sum += tmp; // 무게 추가
            }
            
            time++;
        }
        
        return time + bridge_length;
    }
}