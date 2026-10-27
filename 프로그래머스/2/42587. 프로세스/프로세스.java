import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int N = priorities.length;
        
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            dq.add(new int[]{i, priorities[i]});
        }
        
        int count = 0;
        while(!dq.isEmpty()) {
            int[] cur = dq.poll(); // 실행 대기 큐에서 대기중인 프로세스 하나를 꺼냅니다.
            boolean flag = false;
            for (int i = 0; i < dq.size(); i++) {
                int[] tmp = dq.poll();
                dq.add(tmp);
                if (tmp[1] > cur[1]) flag = true; // 더 큰게 있다.
            }
            if (flag) dq.add(cur); // 더 큰게 있으면 다시 큐에 넣는다.   
            else {
                count++;
                if (cur[0] == location) return count;
            }
        }
        
        
        return 0;
    }
}