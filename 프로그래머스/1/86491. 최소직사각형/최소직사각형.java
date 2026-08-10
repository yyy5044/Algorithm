import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int n = sizes.length;
        
        int a = 0;
        int b = 0;
        int l = 0;
        int s = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            l = Math.max(sizes[i][0], sizes[i][1]);
            s = Math.min(sizes[i][0], sizes[i][1]);
            
            a = Math.max(l, a);
            b = Math.max(s, b);
        }
        
        return a*b;
    }
}