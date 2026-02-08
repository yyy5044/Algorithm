package com.ssafy.algorithm.month2;
import java.io.*;
import java.util.*;

public class Solution_2001_오양호 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 첫 번째 줄
		
		for (int t = 0; t < T; t++) {
			String line = br.readLine(); // 두 번째 줄
			StringTokenizer st = new StringTokenizer(line);
			
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			// System.out.println(N + ", " + M);
			
			int[][] map = new int[N + 1][N + 1]; // 상, 좌 패딩
			int[][] psum = new int[N + 1][N + 1]; // 누적합
			
			for (int i = 1; i < N + 1; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + map[i][j];
				}
			}
			
			int max = 0;
			for (int i = M; i < N + 1; i++) {
				for (int j = M; j < N + 1; j++) {
					if (max < psum[i][j] - psum[i - M][j] - psum[i][j - M] + psum[i - M][j - M]) {
						max = psum[i][j] - psum[i - M][j] - psum[i][j - M] + psum[i - M][j - M];
					}
				}
			}
			
			sb.append("#"+(t+1)+" "+max).append("\n");
		}	
		System.out.println(sb);
	}

}
