import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] citations) {
        Integer[] arr = new Integer[citations.length];
        for (int i = 0; i < citations.length; i++) {
            arr[i] = citations[i];
        }
        
        Arrays.sort(arr, (a, b) -> b - a);
        
        int ans = 0;
        for (int i = 1; i <= arr.length; i++) {
            if (arr[i - 1] >= i) {
                ans = Math.max(i, ans);
            } else break;
        }
            
        return ans;
    }
}