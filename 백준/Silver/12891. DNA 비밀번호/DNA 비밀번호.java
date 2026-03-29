import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // DNA 문자열의 길이
		int P = Integer.parseInt(st.nextToken()); // 부분문자열의 길이
		
		String str = br.readLine(); // DNA 문자열
		
		int[] window = new int[4]; // 현재 윈도우에 있는 문자 카운트 배열
		int[] label = new int[4]; // 사용해야 하는 최소 문자 카운트 배열
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			label[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		for (int i = 0; i < P; i++) { // 밀기 전 최초의 윈도우는 직접 세기
			char ch = str.charAt(i);
			add(ch, window);
		}
		
		if (checkAns(window, label)) ans++;
		
		for (int i = P; i < S; i++) {
			add(str.charAt(i), window);
			subtract(str.charAt(i-P), window);
			if (checkAns(window, label)) ans++;
		}
		
		System.out.println(ans);
	}
	
	private static void add(char ch, int[] window) {
		if (ch == 'A') window[0]++;
		else if (ch == 'C') window[1]++;
		else if (ch == 'G') window[2]++;
		else if (ch == 'T') window[3]++;
	}
	
	private static void subtract(char ch, int[] window) {
		if (ch == 'A') window[0]--;
		else if (ch == 'C') window[1]--;
		else if (ch == 'G') window[2]--;
		else if (ch == 'T') window[3]--;
	}
	
	private static boolean checkAns (int[] window, int[] label) {
		return (window[0] >= label[0] && window[1] >= label[1]
				&& window[2] >= label[2] && window[3] >= label[3]);
	}
}
