import java.io.*;
import java.util.*;

// )))(((()
// u = )))(((, v = ()
//

class Solution {
    public String solution(String p) {
        String answer = dfs(p);

        return answer;
    }
    
    static String dfs(String p) {
        if (p.equals("")) return p;
        
        int cut = cutIdx(p);
        
        String u = p.substring(0, cut);
        String v = p.substring(cut);
        
        System.out.println("u: " + u + ", v: "+ v);
        
        if (isCorrect(u)) {
            return u + dfs(v);
        } else {
            return "(" + dfs(v) + ")" + inverse(u.substring(1, u.length()-1));
        }
    }
    
    static String inverse(String p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        
        return sb.toString();
    }
    
    static boolean isCorrect(String p) {
        int sum = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') sum += 1;
            else if (p.charAt(i) == ')') sum -= 1;
            
            if (sum < 0) return false;
        }
        
        return true;
    }
    
    static int cutIdx(String p) {
        int sum = 0;
        int idx = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') sum += 1;
            else if (p.charAt(i) == ')') sum -= 1;
            
            if (sum == 0) {
                idx = i;
                break;
            }
        }
        
        return idx+1;
    }
}