import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int max = Integer.MIN_VALUE;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        
        for (int i = 0; i < edge.length; i++) {
            int x = edge[i][0];
            int y = edge[i][1];
            
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        
        boolean[] visited = new boolean[n+1];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1, 1}); // 노드번호, 길이
        visited[1] = true;
        
        int[] dist = new int[n+1];
        
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            max = Math.max(max, cur[1]);
            dist[cur[0]] = cur[1];
            
            ArrayList<Integer> tmp = adj.get(cur[0]);
            for (int i = 0; i < tmp.size(); i++) {
                int node = tmp.get(i);
                if (!visited[node]) {
                    dq.add(new int[]{node, cur[1] + 1});
                    visited[node] = true;
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == max) count++;
        }

        return count;
    }
}