import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		int maxHeight = 0;
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[r][c]);
			}
		}
		
		int maxSafeZone = 1; // x가 0일 땐 반드시 1이니까 x=0일 때는 생략하고 1부터 시작
		for (int x = 1; x <= maxHeight; x++) {
			int safeZone = 0; 
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] > x && !visited[r][c]) {
						bfs(r,c,x);
						safeZone++;
					}
				}
			}
			maxSafeZone = Math.max(maxSafeZone, safeZone);
			visited = new boolean[N][N]; // 방문 배열 새 거 줘야 됨
		}
		
		
		System.out.println(maxSafeZone);
		
	}

	public static void bfs(int r, int c, int x) {
		ArrayDeque<int[]> dq = new ArrayDeque<>(); // 배열 생성
		
		visited[r][c] = true; // 미리 찜 해두고
		dq.addLast(new int[]{r,c}); // 첫 요소 집어 넣기
		
		while(!dq.isEmpty()) {
			int[] cur = dq.pollFirst(); // r: cur[0], c: cur[1]
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
				
				if (map[nr][nc] > x) { // 안전 구역만
					visited[nr][nc] = true; // 미리 찜!
					dq.addLast(new int[] {nr, nc}); // 다음 좌표 큐에 삽입	
				}
			}
		}
		
	}
}
