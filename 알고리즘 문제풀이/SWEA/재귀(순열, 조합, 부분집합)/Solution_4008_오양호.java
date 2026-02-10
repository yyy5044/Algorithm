import java.io.*;
import java.util.*;

public class Solution {
	// INPUT
	static int N, M;
	static int[] operators = new int[4];
	static int[] numbers;
	
	// COMBINATION
	static int[] comb;
	static boolean[] visited;
	
	// CALCULATION MAX, MIN
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			
			numbers = new int[N];
			comb = new int[N-1];
			visited = new boolean[N-1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < operators.length; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0, 0);
 			sb.append("#").append(t+1).append(" ").append(max - min).append("\n");
 			
 			max = Integer.MIN_VALUE;
 			min = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
	

	
	public static void combination(int depth, int start, int index) {
		if (depth == operators[index]) {
			if (index == 3) {
				// System.out.println(Arrays.toString(comb));
				int result = numbers[0];
				for (int i = 1; i < N; i++) {
					if(comb[i-1] == 0) {
						result += numbers[i];
					} else if (comb[i-1] == 1) {
						result -= numbers[i];
					} else if (comb[i-1] == 2) {
						result *= numbers[i];
					} else if (comb[i-1] == 3){
						result /= numbers[i];
					}
				}
				if (result > max) max = result;
				if (result < min) min = result;
				return;
			} else {
				combination(0, 0, index+1);
				return;
			}
		}
		
		for (int i = start; i < (N-1); i++) {
			if (visited[i]) continue;
			visited[i] = true;
			comb[i] = index;
			combination(depth+1, i+1, index);
			visited[i] = false;
		}
	}



}