import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 나무 개수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int highest = 0;
			int[] trees = new int[N];
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				highest = Math.max(trees[i], highest);
			}
			
			int ans = Math.min(solve(trees, highest, N), solve(trees, highest+1, N));
			
			sb.append("#").append(t).append(" ")
				.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	private static int solve(int[] trees, int highest, int N) {
		int ans = -1;
		
		int count1 = 0;
		int count2 = 0;
		
		for (int i = 0; i < N; i++) {
			int target = trees[i];
			int diff = Math.abs(highest - target);
			
			count2 += diff / 2;
			count1 += diff % 2;
		}
		
		while(count2 > count1 + 1) {
			count2 -= 1;
			count1 += 2;
		}
		
		if (count1 > count2) {
			ans = 2*count1 - 1;
		} else {
			ans = 2*count2;
		}
		
		return ans;
	}
}
