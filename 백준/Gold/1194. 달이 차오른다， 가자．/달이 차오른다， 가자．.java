import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로: r
		M = Integer.parseInt(st.nextToken()); // 가로: c
		
		visited = new boolean[N][M][1<<6];
		map = new char[N][M];
		int[] start = new int[4]; // 시작 위치 
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == '0') {
					start = new int[] {r, c, 0, 0}; // 시작 위치 저장 (r, c, 000000, 0)
				}
			}
		}
		
		int ans = -1;
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		int start_r = start[0];
		int start_c = start[1];
		int start_s = start[2];
		visited[start_r][start_c][start_s] = true;
		dq.addLast(start);
		
		while(!dq.isEmpty()) {
			int[] cur = dq.pollFirst();
			int r = cur[0];
			int c = cur[1];
			int s = cur[2];
			int cnt = cur[3];
			
			if (map[r][c] == '1') {
				ans = cnt;
				break;
			}
	
			for (int d = 0; d < 4; d++) { // 상하좌우 좌표
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > M-1 || map[nr][nc] == '#') continue; // 인덱스 방어
				
				int cell = map[nr][nc]; // 다음 위치의 값
				int next_s = s; // 다음 상태
				if (cell >= 'a' && cell <= 'f') { // 다음 위치가 만약 키라면
					next_s = next_s|(1<<(cell - 'a')); // 열쇠를 상태에 추가
				}
				else if (cell >= 'A' && cell <= 'F') { // 다음 위치가 문이라면
					if ((next_s&(1<<(cell - 'A'))) == 0) { // 열쇠가 없으면
						continue; // 못 지나감
					}
				}
				
				if (!visited[nr][nc][next_s]) { // 방문을 안 했다면
					visited[nr][nc][next_s] = true;
					dq.addLast(new int[] {nr, nc, next_s, cnt+1});
				}
			}
		}
		
		System.out.println(ans);
		
		// System.out.println(sb);
	}


}
