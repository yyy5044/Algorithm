import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<int[]> dq = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < priorities.length; i++) {
            dq.add(new int[] {i, priorities[i]});
            pq.add(priorities[i]);
        }
        
        int count = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll(); // 프로세스 하나 꺼내기
            
            if (cur[1] >= pq.peek()) { // 더 높은 게 없디면
                pq.poll(); // 실행
                count++;
                if (cur[0] == location) return count;
            } else { // 있다면
                dq.add(cur); // 다시 넣기
            }
        }
        
        return 0;
    }
}