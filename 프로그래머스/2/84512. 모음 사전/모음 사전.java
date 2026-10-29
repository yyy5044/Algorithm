import java.util.*;

class Solution {
    static int N = 5;
    static char[] ch = {'A', 'E', 'I', 'O', 'U'};
    static ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        // A, E, I, O, U로 모든 단어를 만들어서 배열로 저장
        // 사전순으로 정렬
        // 풀 스캔해서 찾으면 인덱스 반환
        // 5의 5승 = 3,125인가? -> 충분히 될듯?
        for (int i = 1; i <= N; i++) {
            dfs(i, 0, "");
        }

        Collections.sort(list);
        // System.out.println(list);
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) return i+1;
        }
        
        return 0;
    }
    
    static void dfs(int n, int depth, String str) {
        if (depth == n) {
            list.add(str);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            dfs(n, depth+1, str + ch[i]);
        }
    }
}