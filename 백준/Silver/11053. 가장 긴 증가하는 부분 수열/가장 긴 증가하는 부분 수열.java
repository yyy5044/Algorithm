import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int ans = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		
	}

}
