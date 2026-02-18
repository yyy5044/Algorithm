import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[] visitedDessert;
	static int maxDessert = -1;
	
	// 순서대로: (우하) (좌하) (좌상) (우상)
	static int[] dr = {1,1,-1,-1};
	static int[] dc = {1,-1,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visitedDessert = new boolean[101];
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					dfs(r, c, 0, 0, new int[]{r, c});
				}
			}

			sb.append("#").append(t+1).append(" ")
				.append(maxDessert).append("\n");
			maxDessert = -1;
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int r, int c, int d, int totalDessert, int[] start) {
		//System.out.println(r+", "+c);
		// 디저트를 1개보다 많이 먹었는데 좌표가 같으면 돌아온 것
		// 종료: 지금까지 먹은 디저트 최대값인지 확인 후 갱신
		if(r==start[0]&&c==start[1]&&totalDessert>0) {
			maxDessert = Math.max(totalDessert, maxDessert);
			return;
		}
		
		for (int i = d; i < 2+d; i++) { // 가던 방향으로 계속 or 방향전환 
			if (d>3) continue;
			int nr = r + dr[d];
			int nc = c + dc[d];
			//System.out.println("nr: " + nr +", "+"nc: "+nc);
			if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visitedDessert[map[nr][nc]]) {
				visitedDessert[map[nr][nc]] = true;
				dfs(nr, nc, i, totalDessert+1, start);
				visitedDessert[map[nr][nc]] = false;
			}
		}
	
	}
}

