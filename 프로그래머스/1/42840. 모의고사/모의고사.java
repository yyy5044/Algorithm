import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[][] patterns = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (patterns[i][j % patterns[i].length] == answers[j]) {
                    arr[i]++;
                }
            }
        }
        
        System.out.println(Arrays.toString(arr));
        int max = Math.max(arr[0], Math.max(arr[1], arr[2]));
        System.out.println(max);
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (arr[i] == max) {
                result.add(i+1);
            }
        }
        
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
    
        
        return ans;
    }
}