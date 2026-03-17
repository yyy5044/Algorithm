import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static int[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static boolean[][] visited;
	static int maxPath = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			maxPath = 0;
			
			ArrayList<int[]> highests = new ArrayList<>();
			int highest = 0;
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					highest = Math.max(map[r][c], highest);
				}
				// System.out.println(Arrays.toString(map[r]));
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == highest) {
						highests.add(new int[] {r, c});
					}
				}
			}
			
			for (int i = 0; i < highests.size(); i++) {
				int[] cur = highests.get(i);
				int result = dfs(cur[0], cur[1], false);
				maxPath = Math.max(result, maxPath);
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(maxPath).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int dfs(int r, int c, boolean isK) {
		int myMax = 1;
		
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
			
			if (map[r][c] > map[nr][nc]) { // 지나갈 수 있나?
				myMax = Math.max(myMax, dfs(nr, nc, isK)+1) ;
			} else if (map[r][c] > map[nr][nc] - K && !isK) { // 공사하면 지나갈 수 있나? 그리고 공사권이 있나?
				isK = true; // 공사권 사용
				int tmp = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				myMax = Math.max(myMax, dfs(nr, nc, isK)+1) ;
				map[nr][nc] = tmp; // 원복
				isK = false;
			}
		}
		
		visited[r][c] = false;
		return myMax;
	}
}
