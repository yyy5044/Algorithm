import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxLong = Integer.MIN_VALUE;
        int maxShort = Integer.MIN_VALUE;
        for (int i = 0; i < sizes.length; i++) {
            
            int lo = Math.max(sizes[i][0], sizes[i][1]);
            int sh = Math.min(sizes[i][0], sizes[i][1]);
            
            maxLong = Math.max(maxLong, lo);
            maxShort = Math.max(maxShort, sh);
        }
        
        return maxLong * maxShort;
    }
}