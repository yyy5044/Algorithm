import java.util.*;
import java.io.*;

public class 제곱 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int ans = iterativePow(C, N);
		System.out.println(ans);
		
		ans = dqPow(C, N);
		System.out.println(ans);
	}
	
	public static int iterativePow(int C, int N) {
		
		int result = 1;
		for (int i = 0; i < N; i++) {
			result *= C;
		}
		
		return result;
	}
	
	public static int dqPow(int C, int N) {
		if (N <= 0) return 1;
		if (N == 1) return C;
		
		int result = dqPow(C, N/2);
		
		if(N%2==0) return result*result;
		else return result*result*C;
	}
}
