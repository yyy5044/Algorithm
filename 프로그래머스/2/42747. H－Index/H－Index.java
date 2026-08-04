import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Integer[] arr = new Integer[citations.length];
        
        for (int i = 0; i < citations.length; i++) {
            arr[i] = citations[i];
        }
        
        Arrays.sort(arr, (a, b) -> b - a);
        // 6 5 3 1 0
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            if (arr[i] >= i+1) {
                h = i+1;
            } else {
                break;
            }
        }
        
        return h;
    }
}