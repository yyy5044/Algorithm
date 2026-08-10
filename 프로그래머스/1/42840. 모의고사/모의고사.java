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
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == patterns[0][i % patterns[0].length]) {
                arr[0]++;
            }
            
            if (answers[i] == patterns[1][i % patterns[1].length]) {
                arr[1]++;
            }
            
            if (answers[i] == patterns[2][i % patterns[2].length]) {
                arr[2]++;
            }
        }
        
        int max = Math.max(arr[0], Math.max(arr[1], arr[2]));
        
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i]) {
                ans.add(i+1);
            }
        }
        
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        
        return result;
    }
}