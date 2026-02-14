import java.util.*;
import java.io.*;

public class Solution {
	static Map<Integer, Integer> memo = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); // 입력: 123
			
			int max = dfs(S);
			
			sb.append("#").append(t+1).append(" ")
				.append(max).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int dfs(int start) {
		if(start<10) return 0;
		
		if(memo.containsKey(start)) return memo.get(start);
		
		int myMax = 0;
		
		String s = String.valueOf(start); // 123 -> "123"
		int numGap = s.length()-1; // 자를 공간 개수: 2
		
		for (int i = 1; i < (1<<numGap); i++) { // i = 01, 10, 11
			int product = 1; // 조각들의 곱을 저장할 변수
			int startIdx = 0;
			for (int j = 0; j < numGap; j++) { // j = 0, 1
				if((i&(1<<j)) != 0) {
					product *= Integer.parseInt(s.substring(startIdx, j+1));
					startIdx = j+1;
				}
			}
			product *= Integer.parseInt(s.substring(startIdx));
			
			myMax = Math.max(myMax, dfs(product)+1) ;
			
		}
		
		memo.put(start, myMax);
		
		return myMax;
	}
	
}
