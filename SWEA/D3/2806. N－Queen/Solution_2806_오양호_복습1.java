import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static boolean[] col, slash, bSlash;
	static int count = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		

		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {		
			N = Integer.parseInt(br.readLine());
			col = new boolean[N+1];
			slash = new boolean[2*N+1];
			bSlash = new boolean[2*N];
			
			setQueen(1);
			
			
			sb.append("#").append(t+1).append(" ")
				.append(count).append("\n");
			
			N = count = 0;
			
		}
		
		System.out.println(sb);
	}
	
	public static void setQueen(int r) {
		
		if(r == N+1) {
			count++;
			return;
		}
		
		for (int i = 1; i <= N; i++) { // 모든 열 시도, 되는 곳 바로 setQueen
			if (col[i] || slash[r+i] || bSlash[r-i+N]) continue;
			
			col[i] = slash[r+i] = bSlash[r-i+N] = true;
			setQueen(r+1);
			col[i] = slash[r+i] = bSlash[r-i+N] = false;	
				
		}
		
		return; // 둘 곳이 없으면 이전 선택으로
	}	
}
