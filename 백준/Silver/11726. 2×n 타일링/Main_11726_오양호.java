import java.io.*;
import java.util.*;

public class Main {
	static int[] dp = new int[1001];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		
		for (int i = 3; i < n+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		int ans = dp[n];
		System.out.println(ans);
	}

}
