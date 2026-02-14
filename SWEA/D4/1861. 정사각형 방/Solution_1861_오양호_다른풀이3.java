import java.util.*;
import java.io.*;


public class Solution {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			boolean[] memo = new boolean[N*N+1];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int d = 0; d < 4; d++) { // 4방 탐색
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
						
						if (map[nr][nc] == map[r][c]+1) {
							memo[map[r][c]] = true;
							break;
						}
					}
				}
			}
			
			int start = 0;
			int curLen = 0;
			int maxLen = Integer.MIN_VALUE;
			for (int i = N*N; i > 0; i--) {
				if (memo[i]) {
					curLen++;
				} else {
					curLen = 0;
				}
				
				if (maxLen <= curLen) {
					maxLen = curLen;
					start = i;
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(start).append(" ").append(maxLen+1).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
