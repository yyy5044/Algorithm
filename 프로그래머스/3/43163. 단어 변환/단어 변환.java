import java.io.*;
import java.util.*;

class Solution {
    static int L ;
    public int solution(String begin, String target, String[] words) {
        L = words[0].length(); // 단어 길이
        
        boolean[] visited = new boolean[words.length];
        Deque<String> sq = new ArrayDeque<>();
        Deque<Integer> q = new ArrayDeque<>();
        sq.add(begin);
        q.add(0);
        
        int ans = 0;
        while(!sq.isEmpty()) {
            String word = sq.poll();
            int length = q.poll();
            
            if (word.equals(target)) {
                ans = length;
                break;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canChange(word, words[i])) {
                    visited[i] = true;
                    sq.add(words[i]);
                    q.add(length + 1);
                }
            }
            
        }
        
        return ans;
    }
    
    static boolean canChange(String start, String end) {
        int diff = 0;
        for (int i = 0; i < L; i++) {
            if (start.charAt(i) != end.charAt(i)) diff++;
        }
        
        if (diff == 1) return true;
        
        return false;
    }
}