import java.io.*;
import java.util.*;

public class Solution {
	static int N, M; // 배열 크기 N*N, 가장자리 제외 M은 코어 개수
	static int[][] map; // 배열
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static ArrayList<int[]> cores;
	static int maxCores = Integer.MIN_VALUE;
	static int minLen = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cores = new ArrayList<>();
			
			for (int r = 1; r < N-1; r++) {
				for (int c = 1; c < N-1; c++) {
					if (map[r][c] == 1) {
						cores.add(new int[] {r, c});
					}
				}
			}
			
			M = cores.size();
			
			dfs(0, 0, 0);
			
			sb.append("#").append(t).append(" ")
				.append(minLen).append("\n");
			
			maxCores = Integer.MIN_VALUE;
			minLen = Integer.MAX_VALUE;
			cores.clear();
		}

		System.out.println(sb);
	}

	// depth: 코어 인덱스, totalLen: 지금까지 설치된 전선 길이, connected: 연결된 코어 개수
	private static void dfs(int depth, int totalLen, int connected) { 
		if (depth == M) { // 종료 조건: 모든 코어에 대해 해봤으면
			if (maxCores < connected) {
				maxCores = connected;
				minLen = totalLen;
			} else if (maxCores == connected) { // 같은 코어 연결 시 최소 전선 길이로 업데이트
				minLen = Math.min(minLen, totalLen);
			}
			
			return;
		}
		
		int[] cur = cores.get(depth);
		int r = cur[0];
		int c = cur[1];
		
		for (int d = 0; d < 4; d++) {
			if (isValid(r, c, d)) { // 설치 가능하면
				int len = setStatus(r, c, d, 2); // 전선 설치
				dfs(depth+1, totalLen + len, connected+1); // 다음 코어로
				setStatus(r, c, d, 0); // 백트래킹 시 원상복구
			}
		}
		
		dfs(depth+1, totalLen, connected); // 코어 연결 안 하는 경우
		
	}
	
	private static boolean isValid(int r, int c, int d) {
		boolean result = true;
		
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) break;
			
			if (map[nr][nc] != 0) { // 경로에 0이 아닌 것이 하나라도 있으면
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	private static int setStatus(int r, int c, int d, int status) { // d 방향 직선으로 status로 값 설정
		int nr = r;
		int nc = c;
		int len = 0; // 바꾼 status 길이
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) break;
			
			map[nr][nc] = status;
			len++;
		}
		
		return len;
	}
	
}
