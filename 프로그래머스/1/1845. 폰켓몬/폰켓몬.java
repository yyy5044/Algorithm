import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int N = nums.length;
        
        for (int n : nums) {
            set.add(n);
        }
        
        if (set.size() >= N/2) {
            return N/2;
        } else {
            return set.size();
        }
    }
}