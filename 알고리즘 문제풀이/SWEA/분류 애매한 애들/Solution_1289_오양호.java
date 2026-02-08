import java.util.*;
import java.io.*;

public class Solution {
	static String N;
	static int[] input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = st.nextToken();
			input = new int[N.length()];
			int cnt = 0;
			for (int i = 0; i < N.length(); i++) {
				input[i] = N.charAt(i) - '0';
			}
			
			for (int i = 0; i < N.length(); i++) {
				if (input[i] == 1) {
					cnt++;
					reverse(input);
				}
			}
		
			sb.append("#").append(t+1).append(" ")
				.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void reverse(int[] input) {
		for (int i = 0; i < N.length(); i++) {
			if (input[i] == 1) input[i] = 0;
			else if(input[i] == 0) input[i] = 1;
		}
	}

}
