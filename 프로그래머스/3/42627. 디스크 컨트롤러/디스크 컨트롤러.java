import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int N = jobs.length;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청 시간으로 정렬
        
        Deque<int[]> dq = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            else if (a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        
        for (int i = 0; i < N; i++) {
            int[] job = jobs[i];
            int[] tmp = new int[] {i, job[0], job[1]};
            dq.add(tmp);
        }
        
        int time = 0;
        int sum = 0; // 총 반환시간
        while (!dq.isEmpty() || !pq.isEmpty()) {
            
            while (!dq.isEmpty() && dq.peek()[1] <= time) { 
                pq.add(dq.poll());
            }
            
            if (pq.isEmpty()) {
                time = dq.peek()[1];
                continue;
            }
            int[] cur = pq.poll();
            
            int job_number = cur[0];
            int req_time = cur[1];  
            int elapse_time = cur[2];
            
            time += elapse_time; // 소요 시간 추가하기
            int turnaround_time = time - req_time;
            sum += turnaround_time;
        }
        
        return sum / N;
    }
}