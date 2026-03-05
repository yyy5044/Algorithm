import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static int[][] map;
	static int[] dr = {0,1,-1,0}; 
	static int[] dc = {-1,0,0,1};
	static boolean[][] visited;
	static int maxPath = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열 크기: N*N
			K = Integer.parseInt(st.nextToken()); // 공사 가능 깊이 
			// (주의!!: 반드시 K를 깎는게 아니라 K까지 깎을 수 있는 것) -> 옆의 땅과 딱 1만큼만 차이나게 깎아야 최적임.
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			// 맵 입력 받으면서 가장 높은 높이 찾기
			int highest = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, map[r][c]);
				}
			}
			
			// 가장 높은 높이의 지형 위치 저장
			ArrayList<int[]> list = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == highest) {
						list.add(new int[] {r, c});
					}
				}
			}
			
			
			for (int i = 0; i < list.size(); i++) {
				int[] cur = list.get(i);
				int r = cur[0];
				int c = cur[1];
				boolean used = false;
				
				int path = dfs(r, c, used);
				maxPath = Math.max(maxPath, path);
			}
			
			sb.append("#").append(t).append(" ")
				.append(maxPath).append("\n");
			
			maxPath = 0;
		}
		
		System.out.println(sb);
	}
	
	private static int dfs(int r, int c, boolean used) { // used: 공사 기회 소진 여부
		visited[r][c] = true;
		
		int myMax = 1;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || visited[nr][nc]) continue;
			
			if (!used) { // 공사 아직 안 했으면
				if (map[r][c] > map[nr][nc]) { // 공사권이 있어도 그냥 지나갈 수 있는 곳은 공사할 필요 x
					myMax = Math.max(myMax, dfs(nr, nc, used)+1);
				} else { // 다음 위치가 현재 위치랑 같거나 크면 공사해서 지나갈 수 있는지 확인
					if (map[r][c] > map[nr][nc] - K) { // 공사해서 지나갈 수 있는지 확인
						int tmp = map[nr][nc];
						map[nr][nc] = map[r][c] - 1; // 현재 위치랑 딱 1차이 나게 공사
						used = true; // 사용한 걸로 바꾸고
						myMax = Math.max(myMax, dfs(nr, nc, used)+1); // 공사한 경우의 수로 진행
						map[nr][nc] = tmp; // 원상복구
						used = false; // 백트래킹 시에는 공사권 초기화
					} 
				}
			} else { // 공사 이미 했으면 여기로
				if (map[nr][nc] < map[r][c]) { // 다음 위치가 현재 위치보다 작아야지만 진행
					myMax = Math.max(myMax, dfs(nr, nc, used)+1);
				}
			}
			
			
			
		}
		
		visited[r][c] = false;
		return myMax;
	}
	
}