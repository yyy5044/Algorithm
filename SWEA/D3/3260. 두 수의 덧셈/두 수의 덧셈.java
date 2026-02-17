import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {	

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String a = st.nextToken();
			String b = st.nextToken();
			
			int[] A = new int[102];
			int[] B = new int[102];
			int[] result = new int[102]; // 101자리 수 두 개 더하면 102자리까지 가능
			
			for (int i = a.length()-1, j = 0; i >= 0; --i, j++) {
				A[j] = a.charAt(i) - '0';
			}
			
			for (int i = b.length()-1, j = 0; i >= 0; --i, j++) {
				B[j] = b.charAt(i) - '0';
			}
			
			int maxLen = Math.max(a.length(), b.length());
			for (int i = 0; i < maxLen; i++) {
				result[i] += (A[i]+B[i]) % 10;
				A[i+1] += (A[i]+B[i]) / 10;
				if(A[maxLen]!=0) result[maxLen] = A[maxLen];
			}
			
			int e = 0;
			for (int i = 101; i >=0; --i) {
				if(result[i] != 0) {
					e = i;
					break;
				}
			}
			
			sb.append("#").append(t+1).append(" ");
			
			for(int i = e; i >= 0; --i) {
				sb.append(result[i]);
			}
					
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}

