import java.util.*;
import java.io.*;


public class Solution {
	static int N;
	static int[][] map, memo;
	static ArrayList<int[]> list = new ArrayList<int[]>();
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int max = Integer.MIN_VALUE;
	static int minNum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			memo = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					list.add(new int[] {i, j});
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				int r = list.get(i)[0];
				int c = list.get(i)[1];
				dfs(r,c,i);
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max == memo[i][j]) {
						minNum = Math.min(map[i][j], minNum);
					} else if (max < memo[i][j]) {
						max = memo[i][j];
						minNum = map[i][j];
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(minNum + " " + max).append("\n");
			
			N = 0;
			max = Integer.MIN_VALUE;
			minNum = Integer.MAX_VALUE;
			list.clear();
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int r, int c, int index) {
		memo[list.get(index)[0]][list.get(index)[1]] += 1;
		for (int d = 0; d < 4; d++) {
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
			if (map[nr][nc] == map[r][c]+1) {
				
				dfs(nr, nc, index);
			}
		}
		
		return;
	}
	
}
