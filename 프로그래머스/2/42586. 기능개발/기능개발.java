import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        int[] days = new int[N];
        // 계산식: 100 - 진행도 / 속도
        for (int i = 0; i < N; i++) {
            days[i] = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] > 0) {
                days[i] += 1;
            }
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            dq.add(days[i]);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(!dq.isEmpty()) {
            int count = 1;
            int cur = dq.poll();
            
            while(dq.peek() != null && dq.peek() <= cur) {
                dq.poll();
                count++;
            }
            
            ans.add(count);
        }
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}