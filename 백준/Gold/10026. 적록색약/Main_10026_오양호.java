import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		char[][] normal = new char[N][N];
		char[][] colorBlind = new char[N][N];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char color = line.charAt(j);
				normal[i][j] = color;
				if (color == 'G') colorBlind[i][j] = 'R';
				else colorBlind[i][j] = color;
			}
		}
		
		int normalCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, normal);
					normalCnt++;
				}
			}
		}
		
		visited = new boolean[N][N];
		int colorBlindCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, colorBlind);
					colorBlindCnt++;
				}
			}
		}
		
		System.out.println(normalCnt + " " + colorBlindCnt);
		
	}
	
	private static void bfs(int r, int c, char[][] map) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		
		char color = map[r][c]; // R
		visited[r][c] = true;
		dq.addLast(new int[] {r,c});
		
		while(!dq.isEmpty()) {
			int[] pos = dq.pollFirst();
			int y = pos[0];
			int x = pos[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = y + dr[d];
				int nc = x + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
				
				if (map[nr][nc] == color) {
					visited[nr][nc] = true;
					dq.addLast(new int[] {nr, nc});
				}
			}
		}
	}
	
}
