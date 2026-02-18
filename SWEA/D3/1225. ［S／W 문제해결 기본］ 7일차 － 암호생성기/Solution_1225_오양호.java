import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	static ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < 10; t++) {
			int test_case = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				int num = Integer.parseInt(st.nextToken());
				dq.addLast(num);
			}
			
			int i = 1;
			while(true) {
				if (i > 5) i = 1;
				int num = dq.pollFirst();
				num -= i;
				if (num <= 0) {
					num = 0;
					dq.addLast(num);
					break;
				} else {
					dq.addLast(num);
				}
				i++;
			}
			
			sb.append("#").append(t+1).append(" ");
			
			for (int j = 0; j < 8; j++) {
				int num = dq.pollFirst();
				sb.append(num).append(" ");
			}

			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}

