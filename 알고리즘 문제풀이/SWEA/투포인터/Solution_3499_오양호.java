import java.util.*;
import java.io.*;


public class Solution {
	static int N;
	static String[] cards, cardsSplitedRight, cardsSplitedLeft;
	static String[] perfectShuffle;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			cards = new String[N];
			perfectShuffle = new String[N];
			st = new StringTokenizer(br.readLine());
			
			if (N % 2 == 0) {
				cardsSplitedRight = new String[(N/2)];
				cardsSplitedLeft = new String[(N/2)];
				
				for (int i = 0; i < N; i++) {
					cards[i] = st.nextToken();
					if (i < (N/2)) {
						cardsSplitedRight[i] = cards[i];
					} else {
						cardsSplitedLeft[i % (N/2)] = cards[i];
					}
				}
			} else {
				cardsSplitedRight = new String[(N/2) + 1];
				cardsSplitedLeft = new String[(N/2)];
				
				for (int i = 0; i < N; i++) {
					cards[i] = st.nextToken();
					if (i <= (N/2)) {
						cardsSplitedRight[i] = cards[i];
					} else {
						cardsSplitedLeft[i % ((N/2)+1)] = cards[i];
					}
				}
			}
			

			// 퍼펙트 셔플
			int idxR = 0, idxL = 0;
			for (int i = 0; i < N; i++) {
				if(i%2 == 0) {
					perfectShuffle[i] = cardsSplitedRight[idxR++];
				} else {
					perfectShuffle[i] = cardsSplitedLeft[idxL++];
				}
			}
			
			sb.append("#").append(t+1).append(" ");
			for (int i = 0; i < N; i++) {
				sb.append(perfectShuffle[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
