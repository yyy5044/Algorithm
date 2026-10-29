import java.util.*;

class Solution {
    static boolean[] visited;
    static int min;
    
    public int solution(int n, int[][] wires) {
        visited = new boolean[n+1];
        min = Integer.MAX_VALUE;
        // 끊어본다
        // 두 전력망의 크기를 확인한다
        // 차이를 min으로 최신화
        
        int[][] adjList = new int[n+1][n+1]; // 0은 버림 -> 인덱스 그대로 쓰기 
        for (int i = 1; i < n; i++) {
            int x = wires[i-1][0];
            int y = wires[i-1][1];
            adjList[x][y] = 1;
            adjList[y][x] = 1;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // i, j -> 끊어볼 연결 쌍
                if (adjList[i][j] != 1) continue;
                
                // 끊어보기
                adjList[i][j] = 0;
                adjList[j][i] = 0;
                
                // 나뉜 전력망 크기 검사
                int a = bfs(n, 1, adjList);
                int b = 0;
                for (int k = 2; k <= n; k++) {
                    if (!visited[k]) {
                        b = bfs(n, k, adjList);
                        break;
                    }
                }
                // 차 구하기
                int diff = Math.abs(a - b);
                
                // min 값 최신화
                min = Math.min(diff, min);
                
                // 복구
                adjList[i][j] = 1;
                adjList[j][i] = 1;
                visited = new boolean[n+1];
            }
        }
        
        return min;
    }
    
    static int bfs(int n, int seed, int[][] adjList) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(seed);
        visited[seed] = true;
        int count = 1;
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            
            for (int i = 1; i <= n; i++) {
                if (adjList[cur][i] == 1 && !visited[i]) {
                    dq.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}