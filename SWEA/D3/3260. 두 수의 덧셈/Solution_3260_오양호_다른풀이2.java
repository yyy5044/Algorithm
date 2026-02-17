import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			BigInteger a = new BigInteger(st.nextToken());
			BigInteger b = new BigInteger(st.nextToken());
			
			sb.append("#").append(t+1).append(" ").append(a.add(b)).append("\n");
		}
		
		System.out.println(sb);
	}
}

