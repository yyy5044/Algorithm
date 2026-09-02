import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            int progress = progresses[i];
            int speed = speeds[i];
            while (progress < 100) {
                progress += speed;
                count++;
            }
            
            days[i] = count;
        }
        
        Queue<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dq.add(days[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            int count = 1;
            while(!dq.isEmpty()) {
                int next = dq.peek();
                if (cur < next) break;
                dq.poll();
                count++;
            }
            
            list.add(count);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
}