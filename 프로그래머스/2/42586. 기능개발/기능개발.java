import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 93: 94 - 95 - 96 - 97 - 98 - 99 - 100 : 7 step
        // 30: 60 - 90 - 120 : 3 step
        // 55: 60 - 65 - 70 - 75 - 80 - 85 - 90 - 95 - 100 : 9 step
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) 
            dq.add(new int[]{progresses[i], speeds[i]});
        
        ArrayList<Integer> ans = new ArrayList<>();

        while (!dq.isEmpty()) {
            int sum = 0;
            while(dq.peek() != null && dq.peek()[0] >= 100) {
                dq.poll(); // 배포
                sum++;
            }

            int n = dq.size();
            for (int i = 0; i < n; i++) {
                int[] cur = dq.poll();
                int progress = cur[0];
                int speed = cur[1];
                progress += speed;
                dq.add(new int[]{progress, speed});
            }
            
            if (sum > 0) ans.add(sum);
        }
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            answer[i] = ans.get(i);
        
        return answer;

    }
}