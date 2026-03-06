import java.io.*;
import java.util.*;

public class Solution {
	static HashMap<Integer, Integer> memo = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int start = Integer.parseInt(br.readLine());
			
			int ans = dfs(start);
			
			sb.append("#").append(t).append(" ")
				.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	
	private static int dfs (int num) {
		if (memo.containsKey(num)) return memo.get(num);
		
		if (num < 10) {
			return 0;
		}
		
		int myMax = 1;
		
		String s = String.valueOf(num);
		int gapNum = s.length() - 1;
		
		for (int i = 1; i < (1<<gapNum); i++) {
			int product = 1;
			int start = 0;
			for (int j = 0; j < gapNum; j++) {
				if ((i&(1<<j)) != 0) {
					product *= Integer.parseInt(s.substring(start, j+1));
					start = j+1;
				}
			}
			
			// 마지막 남은 조각
			product *= Integer.parseInt(s.substring(start));
			
			myMax = Math.max(myMax, dfs(product) + 1);
		}
		
		memo.put(num, myMax);
		
		return myMax;
	}
}
