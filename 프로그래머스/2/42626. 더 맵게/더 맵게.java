import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        
        int count = 0;
        while (!pq.isEmpty()) {
            if (pq.peek() >= K) break;
            int min = pq.poll();
            
            if (pq.isEmpty()) return -1;
            int min_second = pq.poll();
            
            int mix = min + (min_second * 2);
            pq.add(mix);
            
            count++;
        }
        
        return count;
    }
}