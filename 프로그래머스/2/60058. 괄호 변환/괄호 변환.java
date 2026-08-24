import java.io.*;
import java.util.*;

class Solution {
    public String solution(String p) {
        
        String ans = dfs(p);
        
        return ans;
    }
    
    static String dfs(String str) {
        if (str.equals("")) return str;
        
        int idx = splitIdx(str);
        String u = str.substring(0, idx+1);
        String v = str.substring(idx+1);
        
        // System.out.println(u + ", " + v);
        
        if (isCorrect(u)) {
            return u + dfs(v);
        } else {
            return "(" + dfs(v) + ")" + inverse(u.substring(1,u.length()-1));
        }
        
    }
    
    static String inverse(String str) {
        int n = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
    
    static int splitIdx(String str) {
        int n = str.length();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') sum++;
            else sum--;
            
            if (sum == 0) return i;
        }
        
        return n-1;
    }
    
    static boolean isCorrect(String str) {
        int n = str.length();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '(') sum++;
            else sum--;
            
            if (sum < 0) return false;
        }
        
        return true;
    }
}