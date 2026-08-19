import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        
        int prev = arr[0];
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == prev) continue;
            prev = arr[i];
            list.add(arr[i]);
        }
        
        int[] ans = list.stream().mapToInt(i -> i).toArray();
        
        return ans;
    }
}