import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[] operators, operands;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			operands = new int[N];
			operators = new int[N-1];
			
			st = new StringTokenizer(br.readLine());
			int[] tmp = new int[4];
			for(int i = 0, idx = 0; i < 4; i++) {
				tmp[i] = Integer.parseInt(st.nextToken());
				for (int j = 0; j < tmp[i]; j++) {
					operators[idx++] = i;
				}
			}
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				operands[i] = Integer.parseInt(st.nextToken());
			}
			
			do {
				calculator(); // 계산
			} while(np()); // 다음 순열 생성
			
			sb.append("#").append(t+1).append(" ")
				.append(Math.abs(max-min)).append("\n");
			
			N = 0;
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
	
	public static boolean np() {
		int i = operators.length - 1;
		while(i>0 && operators[i-1] >= operators[i]) --i;
		
		if(i == 0) return false;
		
		int j = operators.length - 1;
		while(operators[i-1] >= operators[j]) --j;
		
		swap(i-1, j);
		
		int k = operators.length - 1;
		while(i<k) {
			swap(i++,k--);
		}
		
		return true;
	}
	
	public static void swap(int a, int b) {
		operators[a] = operators[a] ^ operators[b];
		operators[b] = operators[a] ^ operators[b];
		operators[a] = operators[a] ^ operators[b];
	}
	
	public static void calculator() {
		int result = operands[0];
		
		for (int i = 0; i < operators.length; i++) {
			if(operators[i] == 0) {
				result += operands[i+1];
			} else if(operators[i] == 1) {
				result -= operands[i+1];
			} else if(operators[i] == 2) {
				result *= operands[i+1];
			} else if(operators[i] == 3) {
				result /= operands[i+1];
			} else {
				return;
			}
		}
		
		max = Math.max(result, max);
		min = Math.min(result, min);
	}
}
