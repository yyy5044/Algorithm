import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length; // 퍼즐 개수
        int maxLevel = 0;
        for (int d : diffs) maxLevel = Math.max(maxLevel, d);
        
        // System.out.println(maxLevel);
        
        // int ans = 0;
        // for (int level = 1; level < maxLevel; level++) {
        //     if (canSolve(n, limit, level, diffs, times)) {
        //         ans = level;
        //         break;
        //     }
        // }
        
        int lo = 1;
        int hi = maxLevel;
        int mid = (lo + hi) / 2;
        // x x o o o o o
        
        while (true) {
            if (canSolve(n, limit, mid, diffs, times)) { // o
                hi = mid;
                mid = (lo + hi) / 2;
            } else { // x
                lo = mid + 1;
                mid = (lo + hi) / 2;
            }
            
            if (lo == hi) break;
        }
        
        return lo;
    }
    
    static boolean canSolve(int n, long limit, int level, int[] diffs, int[] times) {
        limit -= times[0];
        for (int i = 1; i < n; i++) {
            int diff = diffs[i];
            int time_cur = times[i];
            int time_prev = times[i-1];
            
            if (diff <= level) limit -= time_cur;
            else {
                limit -= (diff - level) * (time_cur + time_prev) + time_cur;
            }
        }
        
        if (limit < 0) return false;
        
        return true;
    }
}