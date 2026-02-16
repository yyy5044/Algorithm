import java.util.*;
import java.io.*;

public class Solution {
	static int[][] map;
	static int[] dx = {-1,1,0};
	static int[] dy = {0,0,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		// int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < 10; t++) {
			int testNum = Integer.parseInt(br.readLine());
			map = new int[100][100];
			// 입력
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int end = 0;
			// 시작점 저장
			for (int x = 0; x < 100; x++) {
				if (map[99][x] == 2) {
					end = x;
				}
			}
			
			int x = end;
			int y = 99;
			while(y > 0) {
				int leftX = x - 1;
				int rightX = x + 1;
				
				if ((leftX >= 0 && leftX <= 99) && map[y][leftX] == 1) {
					map[y][x] = 0;
					x = leftX;
				} else if ((rightX >= 0 && rightX <= 99) && map[y][rightX] == 1) {
					map[y][x] = 0;
					x = rightX;
				} else {
					map[y][x] = 0;
					y--;
				}
				
			}
			
			sb.append("#").append(testNum).append(" ")
				.append(x).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
}

