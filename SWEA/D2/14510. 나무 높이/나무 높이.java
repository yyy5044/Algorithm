import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] trees = new int[N];
			int highest = 0; // 가장 큰 나무
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				highest = Math.max(highest, trees[i]);
			}
			
			int ans = Math.min(solve(highest, trees, N), solve(highest+1, trees, N));
			
			sb.append("#").append(t).append(" ");
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int solve(int highest, int[] trees, int N) {
		int count2 = 0; // 2의 개수
		int count1 = 0; // 1의 개수
		
		for (int i = 0; i < N; i++) {
			int diff = highest - trees[i]; // 각 나무와 diff 계산
			
			count2 += diff / 2; 
			count1 += diff % 2;
		}
		
		// 2의 개수와 1의 개수가 같거나, 2의 개수가 딱 한 개 더 많을 때까지만 쪼개기
		while(count2 > count1 + 1) { 
			count2 -= 1;
			count1 += 2;
		}
		
		int day = 0;
		if (count1 > count2) day = (count1*2) - 1;
		else day = count2*2;
		
		return day;
	}

}

