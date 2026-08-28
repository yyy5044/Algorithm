import java.io.*;
import java.util.*;
// 배포는 선행 기능이 배포 되어야지만 배포될 수 있음
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // progresses: 작업 진행률. (배포되어야 하는 순서대로 저장되어 있음)
        // speeds: 각 작업의 개발 속도
        // 중요: 배포는 하루에 한 번만 가능
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            int progress = progresses[i];
            int speed = speeds[i];
            
            dq.add(new int[]{i, progress, speed});
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        while(!dq.isEmpty()) {
            for (int i = 0; i < dq.size(); i++) {
                int[] tmp = dq.poll();
                int idx = tmp[0], progress = tmp[1], speed = tmp[2];
                dq.add(new int[]{i, progress + speed, speed});
            }
            
            int count = 0;
            while(!dq.isEmpty()) {
                int[] cur = dq.peek();
                if (cur[1] < 100) break; 
                dq.poll();
                count++;
            }
            if (count != 0) list.add(count);
        }
        
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        
        return arr;
    }
}