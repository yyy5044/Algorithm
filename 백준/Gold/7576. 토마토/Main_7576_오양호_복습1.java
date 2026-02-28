import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		ArrayDeque<int[]> dq = new ArrayDeque<>(); // 큐에 토마토 위치 추가
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					visited[i][j] = true; // 빈 상자에는 방문 안 하도록 미리 true 처리
				} else if (map[i][j] == 1) {
					dq.addLast(new int[] {i, j}); // 첫 큐에 저장된 토마토들로부터 퍼지게 할 것
					visited[i][j] = true;
				}
			}
		}
		
		
		int day = 0;
		while(!dq.isEmpty()) {
			int width = dq.size();
			for (int i = 0; i < width; i++) {
				int[] cur = dq.pollFirst();
				int r = cur[0];
				int c = cur[1];
				map[r][c] = 1; // 실제 방문이 일어남 -> 토마토가 익었다!
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 || visited[nr][nc]) continue;
					
					visited[nr][nc] = true; // 큐에 삽입 전에 방문 표시
					dq.addLast(new int[] {nr, nc}); // 다음 위치 큐에 삽입
				}
			}
			day++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					day=0;
					break;
				} 
			}
		}
		
		System.out.println(day-1);
		
	}
	
}
