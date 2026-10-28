import java.util.*;

class Solution {
    static int N; // 던전 개수
    static int[] nums;
    static boolean[] visited;
    static int max;
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        nums = new int[N];
        visited = new boolean[N];
        max = Integer.MIN_VALUE;
        
        perm(0, k, dungeons);
        
        return max;
    }
    
    static void perm(int depth, int k, int[][] dungeons) {
        if (depth == N) {
            int count = 0;
            int tmp_k = k;
            for (int i : nums) {
                int min_k = dungeons[i][0]; // 최소 필요 피로도
                int used_k = dungeons[i][1]; // 소모 피로도

                if (tmp_k < min_k) break; // 최소 필요 피로도를 못 채우면 넘어간다
                tmp_k -= used_k; // 던전 돌았으면 소모 피로도 차감
                count++; // 탐험한 던전 카운트
            }
            
            max = Math.max(count, max);
            
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                nums[depth] = i;
                perm(depth + 1, k, dungeons);
                visited[i] = false;
            }
        }
    }
}