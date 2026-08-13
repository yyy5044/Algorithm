import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> answers = new ArrayList<>();
        for (int x = 0; x < commands.length; x++) {
            int[] command = commands[x];
            int i = command[0];
            int j = command[1];
            int k = command[2];
            
            int len = j - i + 1;
            int[] tmp = new int[len];
            
            for (int y = 0; y < len; y++) {
                tmp[y] = array[y+i-1];
            }
            
            Arrays.sort(tmp);
            
            answers.add(tmp[k-1]);
        }
        
        int[] ans = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) ans[i] = answers.get(i);
        
        return ans;
    }
}