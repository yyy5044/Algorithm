import java.io.*;
import java.util.*;

public class Solution {
	static int N, W, H;
	static int[][] map;
	static int[] dh = {-1,1,0,0}, dw = {0,0,-1,1};
	static int minBlocks = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 치는 횟수
			W = Integer.parseInt(st.nextToken()); // c
			H = Integer.parseInt(st.nextToken()); // r
			
			map = new int[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, map);
			
			sb.append("#").append(t).append(" ")
				.append(minBlocks).append("\n");
			
			minBlocks = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int depth, int[][] currentMap) {
		
		if (depth == N) {
			// 남은 벽돌 개수 세기
			int count = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (currentMap[i][j] != 0) {
						count++;
					}
				}
			}
			
			minBlocks = Math.min(count, minBlocks);
			
			return;
		}
		
		if (minBlocks == 0) return; // 가지치기
		
		for (int i = 0; i < W; i++) {
			int r = 0;
		    for (int h = 0; h < H; h++) {
		        if (currentMap[h][i] != 0) {
		            r = h;
		            break;
		        }
		    }
			
			// 맵 복사
			int[][] copy = new int[H][W];
			for (int h = 0; h < H; h++) {
				copy[h] = currentMap[h].clone();
			}
			
			// 폭발 시뮬레이션
			boom(r, i, copy);
			// 중력 적용
			gravity(copy);
			
			dfs(depth+1, copy);
		}
	}
	
	private static void boom(int r, int c, int[][] currentMap) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {r, c, currentMap[r][c]}); // 좌표, 블럭 번호
		currentMap[r][c] = 0; // 폭발 처리
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int h = cur[0], w = cur[1], range = cur[2];
			
			for (int d = 0; d < 4; d++) {
				int nh = h;
				int nw = w; 
				
				for (int i = 0; i < range - 1; i++) {
					nh += dh[d];
					nw += dw[d];
					
					if (nh < 0 || nw < 0 || nh > H-1 || nw > W-1) continue;
					
					if (currentMap[nh][nw] != 0) {
						dq.add(new int[] {nh, nw, currentMap[nh][nw]});
						currentMap[nh][nw] = 0; // 폭발 처리
					}
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

