import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {	
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0'; 
				}
			}
			
			int center = N / 2;
			int totalProfit = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(Math.abs(center-r)+Math.abs(center-c) > center) continue;
					
					totalProfit += map[r][c];
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(totalProfit).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
