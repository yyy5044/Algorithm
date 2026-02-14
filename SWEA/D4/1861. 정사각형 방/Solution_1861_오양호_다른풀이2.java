import java.util.*;
import java.io.*;


public class Solution {
	static int N;
	static int[][] map, memo;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	static int[] maxRoom = new int[] {Integer.MIN_VALUE, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			memo = new int[N][N]; // 메모이제이션 배열
			for (int r = 0; r < N; r++) {
				st= new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					memo[r][c] = dfs(r, c);
					if(maxRoom[0] == memo[r][c]) {
						maxRoom[1] = Math.min(maxRoom[1], map[r][c]);
					} else if (maxRoom[0] < memo[r][c]) {
						maxRoom[0] = memo[r][c];
						maxRoom[1] = map[r][c];
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(maxRoom[1]).append(" ").append(maxRoom[0]).append("\n");
			
			maxRoom[0] = Integer.MIN_VALUE;
			maxRoom[1] = -1;
			N = 0;
		}
		
		System.out.println(sb);
	}
	
	public static int dfs(int r, int c) {
		if (memo[r][c] != 0) return memo[r][c]; // 이전에 계산되었다면 계산된 값 주고 바로 종료
		
		memo[r][c] += 1; // 자기 방 개수 기록
		
		for (int d = 0; d < 4; d++) { // 들어갈 수 있는 방 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				if (map[nr][nc] == map[r][c]+1) {
					memo[r][c] += dfs(nr, nc); // +1은 자기 방 개수 더한 거
                    break; // 들어갈 수 있는 방은 단 한 개이므로 찾았으면 다른 시도를 차단
				}
			}
		}
		
		return memo[r][c];
	}

	
}
