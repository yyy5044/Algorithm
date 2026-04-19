import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[] x, y;
	static boolean[] visited;
	static long minVector;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			minVector = Long.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			x = new int[N];
			y = new int[N];
			
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0);
			
			sb.append("#").append(t).append(" ");
			sb.append(minVector).append("\n");
			
		}

		System.out.println(sb);

	}
	
	static void combination(int depth, int start) {
		if (depth == (N/2)) {
			int[] sum1 = new int[2];
			int[] sum2 = new int[2];
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum1[0] += x[i];
					sum1[1] += y[i];
				} else {
					sum2[0] += x[i];
					sum2[1] += y[i];
				}
			}
			
			long x_diff = sum1[0] - sum2[0];
			long y_diff = sum1[1] - sum2[1];
			
			minVector = Math.min(minVector, (x_diff*x_diff + y_diff*y_diff));
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			combination(depth+1, i+1);
			visited[i] = false;
		}
	}
}
