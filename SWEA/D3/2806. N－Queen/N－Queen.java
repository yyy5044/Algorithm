import java.io.*;
import java.util.*;

public class Solution {
	static int N, count = 0;
	static boolean[] col, slash, bSlash;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			col = new boolean[N];
			slash = new boolean[2*N];
			bSlash = new boolean[2*N];
			
			setQueen(0);
			
			sb.append("#").append(t+1).append(" ")
				.append(count).append("\n");
			N = 0;
			count = 0;
		}
		
		System.out.println(sb);
	}
	
	public static void setQueen(int r) {
		
		if(r == N) { // 끝까지 오면 경우의 수를 발견한 것
			count++; // 경우의 수
			return;
		}
		
		for (int c = 0; c < N; c++) { // 모든 열에 시도
			if (col[c] || slash[r+c] || bSlash[r-c+N]) {
				continue; // 못 놓으면 다른 열 시도
			}
			col[c] = slash[r+c] = bSlash[r-c+N] = true;
			setQueen(r+1);
			col[c] = slash[r+c] = bSlash[r-c+N] = false;
		}
	}
	
}