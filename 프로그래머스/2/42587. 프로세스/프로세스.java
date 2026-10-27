import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int N = priorities.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int p : priorities) {
            pq.offer(p);
        }
        
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            dq.add(new int[]{i, priorities[i]});
        }
        
        int count = 0;
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            // 우선순위가 더 높은 프로세스가 있다 -> 다시 넣기
            if (pq.peek() != null && pq.peek() > cur[1]) dq.add(cur);
            else { // 없으면 빼고, 내가 찾는 그건지 확인
                count++;
                pq.poll();
                if (cur[0] == location) return count;
            }
        }
        
        return 0;
    }
}