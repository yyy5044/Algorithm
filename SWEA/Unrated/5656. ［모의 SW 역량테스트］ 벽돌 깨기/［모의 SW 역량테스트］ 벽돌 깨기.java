import java.io.*;
import java.util.*;

public class Solution {
	static int N, W, H;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int minBlocks = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 치는 횟수
			W = Integer.parseInt(st.nextToken()); // 너비
			H = Integer.parseInt(st.nextToken()); // 높이
			
			int[][] map = new int[H][W];
			
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, map);
			
			sb.append("#").append(t).append(" ");
			sb.append(minBlocks).append("\n");
			
			minBlocks = Integer.MAX_VALUE;
		}

		System.out.println(sb);

	}
	
	private static void dfs(int depth, int[][] currentMap) {
		int cnt = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (currentMap[h][w] != 0) cnt++;
			}
		}
		
		minBlocks = Math.min(minBlocks, cnt);		
		
		if (minBlocks == 0 || depth == N) {
			return;
		}
		
		for (int i = 0; i < W; i++) {
			int r = -1;
			for (int h = 0; h < H; h++) {
				if (currentMap[h][i] != 0) {
					r = h;
					break;
				}
			}
			
			if (r == -1) continue;
			
			int[][] copy = new int[H][W];
			for (int h = 0; h < H; h++) {
				copy[h] = currentMap[h].clone();
			}
			
			boom(r, i, copy);
			gravity(copy);
			
			dfs(depth+1, copy);
		}
	}
	
	private static void boom(int start_r, int start_c, int[][] currentMap) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {start_r, start_c, currentMap[start_r][start_c]}); // 좌표, 블럭번호
		currentMap[start_r][start_c] = 0; // 폭발 처리
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0], c = cur[1], range = cur[2];
			
			for (int d = 0; d < 4; d++) {
				int nr = r;
				int nc = c;
				for (int i = 0; i < range-1; i++) {
					nr += dr[d];
					nc += dc[d];
					
					if (nr < 0 || nc < 0 || nr > H-1 || nc > W-1 || currentMap[nr][nc] == 0) continue;
					
					dq.add(new int[] {nr, nc, currentMap[nr][nc]});
					currentMap[nr][nc] = 0;
				}
			}
		}
	}
	
	private static void gravity(int[][] currentMap) {
		for (int i = 0; i < W; i++) {
			int index = H-1;
			for (int j = H-1; j >= 0; j--) {
				if (currentMap[j][i] != 0) {
					int tmp = currentMap[j][i];
					currentMap[j][i] = 0;
					currentMap[index][i] = tmp;
					index--;
				}
			}
		}
	}
}