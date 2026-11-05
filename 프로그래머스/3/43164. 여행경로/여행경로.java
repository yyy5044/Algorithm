import java.util.*;

class Solution {
    static int N;
    static boolean[] visited;
    static ArrayList<String> path;
    static String[] answer;
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        visited = new boolean[N];
        path = new ArrayList<>();
        
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        
        path.add("ICN");
        dfs(tickets, 0, "ICN");
        
        return answer;
    }
    
    static void dfs(String[][] tickets, int depth, String from) {
        if (answer != null) return;
        
        if (depth == N) {
            answer = path.toArray(new String[0]);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!tickets[i][0].equals(from) || visited[i]) continue;
            path.add(tickets[i][1]);
            visited[i] = true;
            dfs(tickets, depth + 1, tickets[i][1]);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}