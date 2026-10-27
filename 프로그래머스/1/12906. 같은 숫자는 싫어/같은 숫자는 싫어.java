import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> dq = new ArrayDeque<>();

        for (int a : arr) {
            dq.add(a);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(arr[0]);
        while(!dq.isEmpty()) {
            int n = dq.poll();
            
            if (dq.peek() != null && n != dq.peek()) {
                ans.add(dq.peek());
            }
        }
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) answer[i] = ans.get(i);
        
        return answer;
    }
}