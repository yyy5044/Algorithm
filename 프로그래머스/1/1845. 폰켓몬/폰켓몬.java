import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        int size = map.size();
        int N = nums.length;
        
        if (size >= N/2) { // 모든 포켓몬을 다르게 할 수 있는 경우
            return N/2;
        } else { // 어쩔 수 없이 중복된 포켓몬을 골라야 하는 경우
            return size;
        }
    }
}