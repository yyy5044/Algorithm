import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int[][] map = new int[11][11]; // 받은 좌표 그대로 쓰기 위해 사이즈 +1
			
			int n = Integer.parseInt(br.readLine());
			int count = 0;
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				
				for (int j = x1; j <= x2; j++) {
					for (int k = y1; k <= y2; k++) {
						if (map[j][k] != 0 && map[j][k] != color) {
							count++;
						}
						map[j][k] = color;
					}
				}
					
			}

			sb.append("#").append(t).append(" ");
			sb.append(count).append("\n");
			
		}

		System.out.println(sb);

	}
	
}
