import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int maxMove = 0;
			int roomNum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int[] start = {r,c,1};
					int move = bfs(start);
					if (move == maxMove) { // 이동횟수가 같으면 더 작은 방 번호 기록
						roomNum = Math.min(roomNum, map[r][c]);
					} else if (move>maxMove){
						maxMove = move;
						roomNum = map[r][c];
					}
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(roomNum+" "+maxMove).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(int[] start) {
		int maxCnt = 0;
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited[start[0]][start[1]] = true;
		dq.addLast(start);
		
		while(!dq.isEmpty()) {
			int[] cur = dq.pollFirst();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			
			maxCnt = Math.max(maxCnt, cnt);
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue; // 방이 없을 때 스킵
				if (map[nr][nc] == map[r][c]+1) { // 다음 방 번호가 현재 방 번호보다 딱 1만큼 클 때 입장
					visited[nr][nc] = true;
					dq.addLast(new int[] {nr, nc, cnt+1}); // 다음 방으로 입장, 카운트 하나 늘리기
				}
			}
		}
		
		return maxCnt;
	} 
}
