import java.util.*;

import java.io.*;
class Atom { // 원자클래스: 좌표(x,y), 이동방향(d), 에너지(e)
	int x, y, dir, e;
	public Atom(int x, int y, int dir, int e) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.e = e;
	}
}

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			// INPUT
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String tmp = st.nextToken();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp.charAt(j) - '0';
				}
			}
			
			// center point
			int[] center = new int[] {(N)/2,(N)/2};
			
			int totalFarm = 0;
			for (int r = 0; r < (N+1)/2; r++) {
				for (int c = r; c < N-r; c++) {
					totalFarm += map[r+center[0]][c];
				}
			}
			for (int r = 1; r < (N+1)/2; r++) {
				for (int c = r; c < N-r; c++) {
					totalFarm += map[center[0]-r][c];
				}
			}
			sb.append("#").append(t+1).append(" ")
				.append(totalFarm).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
