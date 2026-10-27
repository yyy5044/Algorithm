import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        Deque<Integer> days = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int d = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] > 0) d += 1;
            days.add(d);
            System.out.println(d);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        while (!days.isEmpty()) {
            int count = 1;
            int day = days.poll();
            while (days.peek() != null && days.peek() <= day) {
                days.poll();
                count++;
            }
            ans.add(count);
        }
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) answer[i] = ans.get(i);
        
        System.out.println(ans);
        
        return answer;
    }
}