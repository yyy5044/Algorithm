import java.util.*;

class Solution {
    static int N = 5;
    static char[] ch = {'A', 'E', 'I', 'O', 'U'};
    static ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        dfs(0, "");
        // System.out.println(list);
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) return i+1;
        }
        
        return 0;
    }
    
    static void dfs(int depth, String str) {
        if (depth == N) {
            return;
        }
        
        for (char c : ch) {
            list.add(str + c);
            dfs(depth+1, str + c);
        }
    }
}