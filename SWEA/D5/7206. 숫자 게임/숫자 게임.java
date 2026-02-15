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
			int S = Integer.parseInt(st.nextToken());
			int max = dfs(S);
			sb.append("#").append(t+1).append(" ")
				.append(max).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int dfs(int start) {
		if (start<10) return 0; // 한 자리 수면 더 이상 쪼갤 수 없음 -> 종료
		
		if (memo.containsKey(start)) return memo.get(start);
		
		int myMax = 1;
		
		String s = String.valueOf(start);
		int gapLen = s.length() - 1;
		
		for (int i = 1; i < (1<<gapLen); i++) { // 반복문 한 번당 자를 수 있는 경우의 수 하나
			int product = 1;
			int startIdx = 0;
			for (int j = 0; j < gapLen; j++) {
				if ((i&(1<<j))!=0) {
					product *= Integer.parseInt(s.substring(startIdx, j+1));
					startIdx = j+1;
				}
			}
			product *= Integer.parseInt(s.substring(startIdx));
			myMax = Math.max(myMax, dfs(product)+1);
		}
		
		memo.put(start, myMax);
		
		return myMax;
	}
}
