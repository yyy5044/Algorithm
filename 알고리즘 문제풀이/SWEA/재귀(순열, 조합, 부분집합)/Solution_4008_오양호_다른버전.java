import java.io.*;
import java.util.*;

public class Solution {
	// MAX, MIN
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	// INPUT
	static int N;
	static int[] operators;
	static int[] operands;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 연산자 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] numOperators = new int[4];
			for (int i = 0; i < 4; i++) {
				numOperators[i] = Integer.parseInt(st.nextToken());
			}
			// 연산자 배열 입력 전처리
			operators = new int[N-1];
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				for(int j = 0; j < numOperators[i]; j++) {
					operators[idx++] = i;
				}
			}
			
			// 피연산자 입력
			operands = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				operands[i] = Integer.parseInt(st.nextToken());
			}
			
			do {
				calculator();
			} while(np());
			
			sb.append("#").append(t+1).append(" ")
				.append(max-min).append("\n");
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
		}
		
		System.out.println(sb);

		
	}

	
	public static boolean np() {
		// 1. 꼭대기 찾기
		int i = N-2;
		while(i>0 && operators[i-1]>=operators[i]) --i;
		
		// 2. 종료
		if (i==0) return false;
		
		// 3. 교환 대상 찾기
		int j = N-2;
		while(operators[i-1]>=operators[j]) --j;
		
		// 4. 교환
		swap(i-1,j);
		
		// 5. 뒷부분 reverse
		int k = N-2;
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
		int result = 0;
		result = operands[0];
		for (int i = 1; i < operands.length; i++) {
			if (operators[i-1] == 0) {
				result += operands[i];
			} else if (operators[i-1] == 1) {
				result -= operands[i];
			} else if (operators[i-1] == 2) {
				result *= operands[i];
			} else if (operators[i-1] == 3) {
				result /= operands[i];
			} else {
				System.out.println("your program is fucked up");
			}
		}
		
		max = Integer.max(result, max);
		min = Integer.min(result, min);
	}
	

}