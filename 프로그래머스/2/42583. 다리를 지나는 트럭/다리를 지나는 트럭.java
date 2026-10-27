import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> trucks = new ArrayDeque<>();
        for (int t : truck_weights) {
            trucks.add(t);
        }
        
        Deque<int[]> bridge = new ArrayDeque<>();
        int sum = 0;
        int time = 0;
        while (!trucks.isEmpty()) {
            int cur = trucks.poll(); // 현재 트럭 무게
             
            if (bridge.peek() != null && time >= bridge.peek()[0] + bridge_length) {
                int[] tmp = bridge.poll(); // 내리고
                sum -= tmp[1]; // 무게 빼고
            }
            
            if (sum + cur <= weight && bridge.size() <= bridge_length) {
                bridge.add(new int[] {time, cur}); // 다리에 탄 시각, 트럭 무게
                sum += cur; // 무게 추가
            } else { // 다리에 못 탔으면
                trucks.addFirst(cur); // 다시 앞에 넣기
            }
            
            time++;
        }
        
        return time + bridge_length;
    }
}