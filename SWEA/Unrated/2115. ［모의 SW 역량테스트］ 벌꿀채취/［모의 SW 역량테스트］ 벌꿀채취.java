import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, C;
	static int[][] map;
	static int[][] maxMap;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			maxMap = new int[N][N-M+1]; // 크기가 M인 덩어리는 한 행에 N-M+1개 나옴
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1. 각 덩어리마다 합이 C를 넘지 않는 최대 이익을 maxMap에 저장
			for (int y = 0; y < N; y++) {
				for (int r = 0; r < N-M+1; r++) {
					int[] block = new int[M];
					for (int c = r; c < M+r; c++) {
						block[c-r] = map[y][c];
					}
					
					int maxPowSum = 0;
					for (int i = 1; i < (1<<M); i++) {
						int sum = 0;
						int powSum = 0;
						for (int j = 0; j < M; j++) { 
							if ((i&(1<<j)) != 0) {
								sum += block[j];
								powSum += block[j]*block[j];
							}
						}
						
						if (sum <= C) { 
							maxPowSum = Math.max(powSum, maxPowSum);
						}
					}
					
					maxMap[y][r] = maxPowSum;
				}
			}
			
			// 2. maxMap에서 일꾼1과 일꾼2가 겹치지 않도록 캐는 모든 경우의 수를 보고 가장 큰 이익을 선택
			int maxSumProfit = Integer.MIN_VALUE;
			for (int r1 = 0; r1 < N; r1++) { 
				for (int c1 = 0; c1 < N-M+1; c1++) { // 일꾼1 출발
					for (int r2 = r1; r2 < N; r2++) {
						int start = (r1==r2)? c1:0;
						for (int c2 = start; c2 < N-M+1; c2++) { // 일꾼2 출발
							if (r1==r2 && start+M>c2) continue; // 같은 행일 때는 겹치면 skip
							int sum = maxMap[r1][c1] + maxMap[r2][c2]; // 일꾼1과 일꾼2의 합이
							maxSumProfit = Math.max(maxSumProfit, sum); // 현재의 최대보다 크면 업데이트
						}
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(maxSumProfit).append("\n");
		}
		
		System.out.println(sb);
	}

}

