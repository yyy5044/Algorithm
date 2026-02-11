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
			
			count = 0;
			N = 0;
		}
		
		System.out.println(sb);
	}
	
	public static void setQueen(int r) {
		// 종료조건
		if (r == N) {
			count++;
			return;
		}

		// 해당 행의 모든 열에 퀸 둬보기
		for (int i = 0; i < N; i++) {
			// 가지치기
			if (col[i] || slash[r+i] || bSlash[(r-i)+N]) continue;
			
			col[i] = slash[r+i] = bSlash[(r-i)+N] = true;
			setQueen(r+1);
			col[i] = slash[r+i] = bSlash[(r-i)+N] = false;
		}
		
	}
	

}