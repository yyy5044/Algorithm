import java.io.*;
import java.util.*;

public class Solution {
	static int[][] memo;
	static int N;
	static int[] start, end;
	static int[][] customers;
	static int[] index;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 고객 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new int[2];
			end = new int[2];
			memo = new int[11][11];
			
			// 시작 위치: 회사
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			// 도착 위치: 집
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			index = new int[N];
			for (int i = 0; i < N; i++) {
				index[i] = i;
			}
			
			int minPath = Integer.MAX_VALUE;
			do {
				boolean flag = false;
				int sum = Math.abs(start[0] - customers[index[0]][0]) 
                        + Math.abs(start[1] - customers[index[0]][1]); // 출발-첫고객 더하고 시작
				for (int i = 0; i < N-1; i++) {
					sum += calPath(index[i], index[i+1]);
					
					if (sum > minPath) { // 가지치기
						flag = true;
						break;
					}
				}
				
				if (flag) continue;
				
				sum += Math.abs(end[0] - customers[index[N-1]][0]) // 마지막 고객-집
                        + Math.abs(end[1] - customers[index[N-1]][1]);
				
				minPath = Math.min(minPath, sum);
			}while (np());
			
			sb.append("#").append(t).append(" ");
			sb.append(minPath).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int calPath(int c1, int c2) {
		if (memo[c1][c2] != 0) return memo[c1][c2];
		
		int result = Math.abs(customers[c1][0] - customers[c2][0]) + Math.abs(customers[c1][1] - customers[c2][1]);
		memo[c1][c2] = result;
		
		return result;
	}
	
	private static boolean np() {
		// 1. 꼭대기 찾기
		int i = N-1;
		while(i > 0 && index[i-1] >= index[i]) --i;
		
		// 2. 꼭대기 체크
		if (i <= 0) return false;
		
		// 3. 교환 대상 찾기
		int j = N-1;
		while (index[i-1] >= index[j]) --j;
		
		// 4. 교환
		swap(i-1, j);
		
		// 5. 뒷부분 정렬
		int k = N-1;
		while(i<k) {
			swap(i++,k--);
		}
		
		return true;
	}
	
	private static void swap(int a, int b) {
		index[a] ^= index[b];
		index[b] ^= index[a];
		index[a] ^= index[b];
	}
}