import java.io.*;
import java.util.*;

public class Solution {
	static HashMap<Integer, Integer> memo = new HashMap<>(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		

		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int num = Integer.parseInt(br.readLine());
			
			int n = dfs(num);
			
			sb.append("#").append(t).append(" ")
				.append(n).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int dfs(int num) {
		
		int myMax = 1; // 내 턴수
		
		if (memo.containsKey(num)) return memo.get(num);
		
		if(num < 10) { // 10이하면 중지
			return 0;
		}
		
		String s = String.valueOf(num); // 123 -> "123"
		int gapLen = s.length() - 1; // 3 - 1 = 2
		
		for (int i = 1; i < (1<<gapLen); i++) {
			int product = 1;
			int start = 0;
			for (int j = 0; j < gapLen; j++) {
				if((i&(1<<(gapLen-j-1))) != 0) { // 자르기
					product *= Integer.parseInt(s.substring(start, j+1));
					start = j+1;
				}
				
			}
			// 마지막 조각 처리
			product *= Integer.parseInt(s.substring(start));
			myMax = Math.max(myMax, dfs(product)+1);
		}
		
		memo.put(num, myMax);
		
		return myMax;
	}
	
}
