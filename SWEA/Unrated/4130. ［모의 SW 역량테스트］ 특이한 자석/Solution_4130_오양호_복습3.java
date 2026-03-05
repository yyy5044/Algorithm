import java.io.*;
import java.util.*;

public class Solution {
	static int[][] magnets = new int[4][8]; // 자석 4개, 자석 당 8개의 날이 존재
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine()); // 명령 횟수
			
			for (int i = 0; i < 4; i++) { // 자석 4개 입력
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) { // 회전 명령 입력
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken())-1;
				int dir = Integer.parseInt(st.nextToken());
				
				dfs(idx, -1, dir); // 회전 수행
			}
			
			int score = 0;
			for (int i = 0; i < 4; i++) {
				if (magnets[i][0] == 1) {
					score += (1<<i);
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(score).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int curIdx, int fromIdx, int rotateDir) {
		// 내 오른쪽이 있고, 나를 호출한 것이 왼쪽 또는 외부
		if (curIdx + 1 <= 3 && curIdx + 1 != fromIdx) { 
			if (magnets[curIdx][2] != magnets[curIdx+1][6]) { // 극이 다르면 오른쪽 전이
				dfs(curIdx+1, curIdx, -rotateDir);
			}
		}
		
		// 내 왼쪽이 있고, 나를 호출한 것이 오른쪽 또는 외부
		if (curIdx - 1 >= 0 && curIdx - 1 != fromIdx) { 
			if (magnets[curIdx][6] != magnets[curIdx-1][2]) { // 극이 다르면 왼쪽 전이
				dfs(curIdx-1, curIdx, -rotateDir);
			}
		}
		
		rotate(curIdx, rotateDir);
	}
	
	private static void rotate(int idx, int d) { // 합격
		int n = 8; // 자석 길이
		if (d > 0) { // 양수 입력: 시계 방향 회전
			int tmp = magnets[idx][n-1];
			for (int i = n-1; i >= 1; i--) {
				magnets[idx][i] = magnets[idx][i-1];
			}
			magnets[idx][0] = tmp;
		} else if (d < 0) { // 음수 입력: 반시계 방향 회전
			int tmp = magnets[idx][0];
			for (int i = 0; i < n-1; i++) {
				magnets[idx][i] = magnets[idx][i+1];
			}
			magnets[idx][n-1] = tmp;
		}
	}
}

