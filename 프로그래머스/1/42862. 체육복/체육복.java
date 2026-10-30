import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        
        Set<Integer> set = new HashSet<>(); // 여벌 있는 사람
        for (int r : reserve) set.add(r);
        
        for (int i = 0; i < lost.length; i++) {
            int l = lost[i];
            if (set.contains(l)) {
                set.remove(l);
                lost[i] = -1;
            }
        }
        
        for (int l : lost) {
            if (l == -1) continue;
            if (set.contains(l - 1)) {
                set.remove(l - 1);
            } else if (set.contains(l + 1)) {
                set.remove(l + 1);
            } else n--;
        }

        return n;
    }
}