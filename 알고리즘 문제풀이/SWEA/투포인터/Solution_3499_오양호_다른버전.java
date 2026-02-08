import java.util.*;
import java.io.*;


public class Solution {
	static int N;
	static String[] cards;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			sb.append("#").append(t+1).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cards = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = st.nextToken();
			}
			
			int r = 0, l = (N+1)/2;
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					sb.append(cards[r++]).append(" ");
				} else {
					sb.append(cards[l++]).append(" ");
				}
			}
			sb.append("\n");
		}


		System.out.println(sb);
	}

}
