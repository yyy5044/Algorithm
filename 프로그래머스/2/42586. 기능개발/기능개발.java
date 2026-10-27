import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // [7, 3, 9]
        // 7보다 작은 지 확인, 큰 거 나오면 stop
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int day = 0;
            while(progress < 100) {
                progress += speeds[i];
                day++;
            }
            
            days[i] = day;
        }
        
        Deque<Integer> dq = new ArrayDeque<>();
        for (int d : days) {
            dq.add(d);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            int count = 1;
            while (!dq.isEmpty()) {
                if (dq.peek() != null && dq.peek() <= cur) {
                    dq.poll();
                    count++;
                } else break;
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