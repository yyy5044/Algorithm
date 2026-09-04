import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int n = priorities.length;
        Queue<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dq.add(new int[]{i, priorities[i]});
        }
        
        int ans = -1;
        int count = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            
            boolean hasHigher = false;
            for (int[] q : dq) {
                if (q[1] > cur[1]) {
                    hasHigher = true;
                    break;
                }
            }
            
            if (hasHigher) {
                dq.add(cur);
            } else {
                count++;
                if (cur[0] == location) {
                    ans = count;
                    break;
                }
            }
        }
        
        return ans;
    }
}