import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {0,-1,1,0,0}, dc = {0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			// 1. input
			// 2. data structure: Queue<int[]>
			// input all elements(r, c, d, number of microbes) in the Queue
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // size of map: N*N
			int M = Integer.parseInt(st.nextToken()); // time passed
			int K = Integer.parseInt(st.nextToken()); // number of microorganisms
			
			int[][] microbes = new int[K][4]; // [0]-[1]: (r, c), [2]: number of microbes, [3]: direction 
			
			ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				microbes[i] = new int[] {r, c, num, d};
				dq.add(new int[] {r, c, num, d});
			}

			
			for (int time = 0; time < M; time++) {
				// 3. prepare new map named temp
				int[][][] temp = new int[N][N][3]; // [0]: num, [1]: biggest, [2]: direction of biggest
				
				while(!dq.isEmpty()) {
					int[] cur = dq.poll();
					int r = cur[0], c = cur[1], num = cur[2], d = cur[3];
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr>=0&&nc>=0&&nr<N&&nc<N) {
						temp[nr][nc][0] += num;
						
						if (temp[nr][nc][1] <= num) {
							temp[nr][nc][1] = num;
							temp[nr][nc][2] = d;
						}
					}
				}
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (temp[r][c][0] != 0) {
							int num = temp[r][c][0];
							int d = temp[r][c][2];
							
							if (r == 0 || c == 0 || r == N-1 || c == N-1) {
								num = num / 2;
								if (d == 1) d = 2;
								else if (d == 2) d = 1;
								else if (d == 3) d = 4;
								else if (d == 4) d = 3;
							}
							
							dq.add(new int[] {r,c,num,d});
						} 
					}
				}
				
			}
			
			
			int ans = 0;
			while(!dq.isEmpty()) {
				int[] cur = dq.poll();
				ans += cur[2];
			}
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

}

