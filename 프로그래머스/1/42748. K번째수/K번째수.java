import java.io.*;
import java.util.*;
// 4:07
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int n = commands.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int[] command = commands[i]; // command = [2, 5, 3]
            int start = command[0] - 1; // start = 1
            int end = command[1]; // end = 5
            int k = command[2]; // k = 3
            
            int l = end - start; // l = 5 - 1 = 4
            int[] arr = new int[l];
            for (int j = 0; j < l; j++) {
                arr[j] = array[start + j];
            }
            
            Arrays.sort(arr);
            ans[i] = arr[k-1];
        }
        
        return ans;
    }
}