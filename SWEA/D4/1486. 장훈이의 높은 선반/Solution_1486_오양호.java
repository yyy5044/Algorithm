import java.util.*;
import java.io.*;


public class Solution {
	static int[] heights;
	static int N, B;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("#").append(t+1).append(" ")
				.append(min).append("\n");
			N = B = 0;
			min = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int sum) {
		
		if (sum == B) {
			min = 0;
			return;
		}
		
		if (sum > B) {
			if ((sum - B) <= min) {
				min = (sum - B);
			}
			return;
		}

		if (depth == N) {
			return;
		}
		
		dfs(depth+1, sum+heights[depth]);
		dfs(depth+1, sum);
		
	}
	
}
