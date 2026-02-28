import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // M: 가로(c)
		int N = Integer.parseInt(st.nextToken()); // N: 세로(r)
		
		int[][] map = new int[N][M];
		int count = 0;
		int tomatoCnt = 0;
		
		ArrayDeque<int[]> dq = new ArrayDeque<int[]>(); // 큐
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					dq.addLast(new int[] {i, j});
					count++;
				}
				
				if (map[i][j] != -1) tomatoCnt++;
			}
		}
		
		int day = 0;
		while(!dq.isEmpty()) {
			int size = dq.size();
			for (int step = 0; step < size; step++) {
				int[] cur = dq.pollFirst();
				
				int r = cur[0];
				int c = cur[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1) continue;
					
					if (map[nr][nc] == 0) {
						map[nr][nc] = 1;
						dq.addLast(new int[] {nr, nc});
						count++;
					}
				}
			}
			
			day++;
		}
		
		if (count != tomatoCnt) day = 0;
		
		System.out.println(day-1);
	}


}
