import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums) {
            set.add(n);
        }
        
        int ans = Math.min(nums.length/2, set.size());
        
        return ans;
    }
}