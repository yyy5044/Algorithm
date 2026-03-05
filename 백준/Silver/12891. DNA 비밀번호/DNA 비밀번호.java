import java.io.*;
import java.util.*;

public class Main {
	static int S, P;
	static int[] ACGT = new int[4]; // 사용해야 하는 최소 문자수
	static int[] window = new int[4]; // 윈도우에 들어있는 문자수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // 문자열 길이
		P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
		
		String DNA = br.readLine(); // 문자열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ACGT[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		
		for (int i = 0; i < P; i++) { // 초기 window 세팅
			char ch = DNA.charAt(i);
			addChar(ch);
		}
		
		if (isValid()) count++;
		
		for (int i = P; i < S; i++) {
			int j = i - P;
			
			addChar(DNA.charAt(i));
			removeChar(DNA.charAt(j));
			
			if (isValid()) count++;
		}
		
		System.out.println(count);
	}
	
	private static void addChar(char ch) {
		if (ch == 'A') window[0]++;
		else if (ch == 'C') window[1]++;
		else if (ch == 'G') window[2]++;
		else if (ch == 'T') window[3]++;
	}
	
	private static void removeChar(char ch) {
		if (ch == 'A') window[0]--;
		else if (ch == 'C') window[1]--;
		else if (ch == 'G') window[2]--;
		else if (ch == 'T') window[3]--;
	}
	
	private static boolean isValid() {
		return (window[0] >= ACGT[0] && window[1] >= ACGT[1] &&
				window[2] >= ACGT[2] && window[3] >= ACGT[3]);
	}
	
}
