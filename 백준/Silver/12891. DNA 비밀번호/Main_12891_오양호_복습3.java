import java.io.*;
import java.util.*;

public class Main {
	static int[] window;
	static int[] constraint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String line = br.readLine();
		
		window = new int[4];
		constraint = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			constraint[i] = Integer.parseInt(st.nextToken());
		}
		
		// window initialize
		for (int i = 0; i < P; i++) {
			add(line.charAt(i));
		}
		
		if (check()) ans++;
		
		for (int i = P; i < S; i++) {
			add(line.charAt(i));
			sub(line.charAt(i-P));
			if (check()) ans++;
		}
		
		System.out.println(ans);
	}
	
	static void add(char ch) {
		if (ch == 'A') window[0]++;
		else if (ch == 'C') window[1]++;
		else if (ch == 'G') window[2]++;
		else if (ch == 'T') window[3]++;
	}
	
	static void sub(char ch) {
		if (ch == 'A') window[0]--;
		else if (ch == 'C') window[1]--;
		else if (ch == 'G') window[2]--;
		else if (ch == 'T') window[3]--;
	}
	
	static boolean check() {
		return (window[0] >= constraint[0] && window[1] >= constraint[1]
				&& window[2] >= constraint[2] && window[3] >= constraint[3]);
	}
}
