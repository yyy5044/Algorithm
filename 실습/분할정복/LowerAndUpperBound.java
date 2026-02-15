import java.util.*;
import java.io.*;

//입력 1:
//8
//2 4 5 10 10 15 15 20
//10
//
//입력 2:
//8
//2 4 5 10 10 15 15 20
//15

public class LowerAndUpperBound {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int target = Integer.parseInt(br.readLine());
		
		Arrays.sort(input); // 결코 정렬! 결코 정렬!
		
		int ans = lowerBound(input, target);
		System.out.println("Lower Bound: " + ans);
		
		ans = upperBound(input, target);
		System.out.println("Upper Bound: " + ans);
		
		
//		int T = Integer.parseInt(br.readLine());	
//		for (int t = 0; t < T; t++) {			
//			sb.append("#").append(t+1).append(" ")
//				.append().append("\n");
//		}
		
		System.out.println(sb);
	}
	
	public static int lowerBound(int[] arr, int target) {
		int s = 0, e = arr.length, m = 0;
		
		while (s<e) {
			m = (s+e)/2;
			if(arr[m]<target) s = m+1;
			else e = m;
		}
		
		return e;
	}
	
	public static int upperBound(int[] arr, int target) {
		int s = 0, e = arr.length, m = 0;
		
		while (s<e) {
			m = (s+e)/2;
			if(arr[m]<=target) s = m+1;
			else e = m;
		}
		
		return e;
	}
		
}
