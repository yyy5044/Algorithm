import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			// INPUT START ---
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 맵 크기
			int M = Integer.parseInt(st.nextToken()); // 집 하나 당 예산
			int[][] map = new int[N][N]; // 맵
			ArrayList<int[]> homes = new ArrayList<>(); // 집 좌표 저장 리스트
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 1) homes.add(new int[] {i, j}); // r(y), c(x)
				}
			}
			// --- INPUT END
			
			
			// SOLVING
			int maxHomeCnt = Integer.MIN_VALUE;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int k = 1; k <= 21; k++) {
						int homeCnt = 0; // 서비스 가능 가구 수
						for (int i = 0; i < homes.size(); i++) {
							int y = homes.get(i)[0];
							int x = homes.get(i)[1];
							if (Math.abs(y - r)+Math.abs(x - c) < k) {
								homeCnt++; // 집이 서비스 안에 들어옴
							}
						}
						
						if (homeCnt*M >= (k*k) + ((k-1)*(k-1))) { // 손해 안 볼 때만
							maxHomeCnt = Math.max(maxHomeCnt, homeCnt); // 최대 가구 갱신
						}
					}
				}
			}

			sb.append("#").append(t).append(" ");
			sb.append(maxHomeCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
}

