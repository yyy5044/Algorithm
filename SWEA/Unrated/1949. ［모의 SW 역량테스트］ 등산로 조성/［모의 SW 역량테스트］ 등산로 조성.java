import java.util.*;
import java.io.*;

public class Solution {
	static int N, K;
	static boolean[][] visited;
	static int[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			int maxHeight = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			// 가장 높은 봉우리 저장
			ArrayList<int[]> startPoints = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						startPoints.add(new int[] {i, j});
					}
				}
			}
			
			int longest = 0;
			// 각 위치(가장 높은 봉우리)에서 시작해보기 
			for (int i = 0; i < startPoints.size(); i++) {
				int r = startPoints.get(i)[0];
				int c = startPoints.get(i)[1];
				boolean isUsed = false;
				int cntPath = dfs(r, c, isUsed, visited);
				longest = Math.max(longest, cntPath);
			}
	
			sb.append("#").append(t+1).append(" ")
				.append(longest).append("\n");
			
			N = K = 0;
		}
		
		System.out.println(sb);
	}

	public static int dfs(int r, int c, boolean isUsed, boolean[][] visited) {
		visited[r][c] = true;
		int myCnt = 1;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;	
			
			if (map[nr][nc] < map[r][c])  { // 다음 갈 곳이 현재 위치보다 낮아야 함.
				myCnt = Math.max(myCnt, dfs(nr, nc, isUsed, visited) + 1);
			} else if(!isUsed && (map[nr][nc] - K < map[r][c])) {
				int tmp = map[nr][nc];
				map[nr][nc] = map[r][c] - 1; // 현재 위치보다 한 칸만 낮게 공사
				myCnt = Math.max(myCnt, dfs(nr, nc, true, visited) + 1); // 공사 기회를 쓰는 루트로 쭉 감
				map[nr][nc] = tmp; // 공사한 것도 복구
			}
		}
		visited[r][c] = false;
		return myCnt;
	}
}

