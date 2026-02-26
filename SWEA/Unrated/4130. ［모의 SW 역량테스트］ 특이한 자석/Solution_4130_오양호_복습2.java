import java.io.*;
import java.util.*;

public class Solution {
	static int K; // 회전 명령 개수
	static int N = 4, blades = 8; // 자석 개수, 한 개의 자석의 총 날의 개수
	static int[][] magnets = new int[N][blades];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < blades; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int curIdx = Integer.parseInt(st.nextToken()) - 1;
				int rotateDir = Integer.parseInt(st.nextToken());
				dfs(curIdx, rotateDir, 99);
			}
			
			int score = 0;
			for (int i = 0; i < N; i++) {
				if (magnets[i][0] == 1) {
					score += (1<<i);
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(score).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// curIdx: 현재 자석 번호, rotateDir: 회전방향, fromIdx: 나를 호출한 자석번호
	private static void dfs(int curIdx, int rotateDir, int fromIdx) {
		if(curIdx+1<N && curIdx+1 != fromIdx) { // 오른쪽이 있고, 나를 호출한 자석이 왼쪽일 때
			// 오른쪽 자석과 맞닿은 면 확인해보고 다른 극이면 전파
			if (magnets[curIdx][2] != magnets[curIdx+1][6]) {
				dfs(curIdx+1, -rotateDir, curIdx);
			}
		} 
		if (curIdx-1>=0 && curIdx-1 != fromIdx) { // 왼쪽이 있고, 나를 호출한 자석이 오른쪽일 때
			// 왼쪽 자석과 맞닿은 면 확인해보고 다른 극이면 전파
			if (magnets[curIdx][6] != magnets[curIdx-1][2]) {
				dfs(curIdx-1, -rotateDir, curIdx);
			}
		}
		
		rotate(curIdx, rotateDir); // 회전
	}
	
	private static void rotate(int curIdx, int rotateDir) {
		int n = magnets[curIdx].length;
		
		if (rotateDir > 0) {
			int tmp = magnets[curIdx][n-1];
			for (int i = n-1; i > 0; i--) {
				magnets[curIdx][i] = magnets[curIdx][i-1];
			}
			magnets[curIdx][0] = tmp;
		} else if (rotateDir < 0) {
			int tmp = magnets[curIdx][0];
			for (int i = 0; i < n-1; i++) {
				magnets[curIdx][i] = magnets[curIdx][i+1];
			}
			magnets[curIdx][n-1] = tmp;
		}
	}
}
