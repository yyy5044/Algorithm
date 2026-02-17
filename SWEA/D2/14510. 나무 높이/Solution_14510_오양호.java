import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {	
			int N = Integer.parseInt(br.readLine());
			int[] input = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
	 		for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
	 		
	 		int biggest = 0;
	 		for (int i = 0; i < N; i++) {
	 			biggest = Math.max(input[i], biggest);
	 		}
	 		
	 		int count1 = 0; // 필요한 1의 개수
	 		int count2 = 0; // 필요한 2의 개수
	 		for (int i = 0; i < N; i++) {
	 			int diff = biggest - input[i];
	 			if(diff>0) {
	 				count2 += diff/2;
	 				count1 += diff%2;
	 			}
	 		}
	 		
	 		while(count2>count1+1) { // 필요한 2의 개수가 필요한 1의 개수보다 2개 이상 많은 동안만 반복
	 			count2 = count2 - 1; // 2의 개수 하나 줄이고,
	 			count1 = count1 + 2; // 1의 개수 두개 올리면 2 하나를 1 두 개로 쪼갠 효과
	 		}
	 		
	 		int day = 0;
	 		if (count1 > count2) { // count1의 개수가 더 많으면
	 			day = count1*2 - 1; // count1의 개수에 의해 최소 일수 결정
	 		} else { // 그게 아니면
	 			day = count2*2; // count2의 개수에 의 최소 일수 결정
	 		}
	 		
			sb.append("#").append(t+1).append(" ")
				.append(day).append("\n");
		}
		
		System.out.println(sb);
	}

}

