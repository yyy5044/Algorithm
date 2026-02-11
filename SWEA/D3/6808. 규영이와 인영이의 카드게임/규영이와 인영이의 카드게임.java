import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] isSelected = new boolean[18];
	static int[] q0 = new int[9];
	static int[] in0 = new int[9];
	static int[] perm = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
	static int win = 0, lose = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				q0[i] = Integer.parseInt(st.nextToken());
				isSelected[q0[i] - 1] = true;
			}
			
			for (int i = 0, idx = 0; i < 18; i++) {
				if(!isSelected[i]) {
					in0[idx++] = i + 1;
				}
			}
			
			do {
				game();
			} while (np());
			
			sb.append("#").append(t+1).append(" ")
				.append(win).append(" ").append(lose).append("\n");
			win = 0;
			lose = 0;
			perm = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
			isSelected = new boolean[18];
		}
		
		System.out.println(sb);
	}
	
	public static void game() {
		int q0Score = 0;
		int in0Score = 0;
		for (int i = 0; i < 9; i++) {
			if (q0[i] > in0[perm[i]]) {
				q0Score += q0[i] + in0[perm[i]];
			} else {
				in0Score += q0[i] + in0[perm[i]];
			}
		}
		
		if (q0Score > in0Score) win++;
		else if (q0Score < in0Score) lose++;
	}
	
	public static boolean np() {
		// 1. 꼭대기 찾기
		int i = 9 - 1;
		while(i>0 && perm[i-1]>=perm[i]) --i;
		
		// 2. 종료: 더 이상 다음 순열이 없음
		if (i == 0) return false;
		
		// 3. 교환 대상 찾기
		int j = 9 - 1;
		while(perm[i-1]>=perm[j]) --j;
		
		// 4. swap
		swap(i-1, j);
		
		// 5. reverse
		int k = 9 - 1;
		while(i<k) {
			swap(i++,k--);
		}
		
		return true;
		
	}
	
	public static void swap(int a, int b) {
		perm[a] = perm[a] ^ perm[b];
		perm[b] = perm[a] ^ perm[b];
		perm[a] = perm[a] ^ perm[b];
	}
	

}