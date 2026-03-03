import java.io.*;
import java.util.*;

public class Main {
	static int S, P, count = 0;
	static int[] constraints = new int[4];
	static String DNA;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		P = Integer.parseInt(st.nextToken()); // 비밀번호의 길이
		
		DNA = br.readLine(); // 임의의 DNA
		
		st = new StringTokenizer(br.readLine());
		constraints[0] = Integer.parseInt(st.nextToken()); // A
		constraints[1] = Integer.parseInt(st.nextToken()); // C
		constraints[2] = Integer.parseInt(st.nextToken()); // G
		constraints[3] = Integer.parseInt(st.nextToken()); // T
		
		int cntA = 0, cntC = 0, cntG = 0, cntT = 0;
		
		int s = 0, e = 0;
		while(true) {
			if (s+P < e) { // s를 늘리는 경우
				if (DNA.charAt(s) == 'A') cntA--;
				else if (DNA.charAt(s) == 'C') cntC--;
				else if (DNA.charAt(s) == 'G') cntG--;
				else if (DNA.charAt(s) == 'T') cntT--;
				s++;
			} else if (e == S) { // 종료
				break;
			} else { // e를 늘리는 경우
				if (DNA.charAt(e) == 'A') cntA++;
				else if (DNA.charAt(e) == 'C') cntC++;
				else if (DNA.charAt(e) == 'G') cntG++;
				else if (DNA.charAt(e) == 'T') cntT++;
				e++;
			}
			
			
			if (e-s == P) { // 길이가 P일 때
				// 해당 문자열이 가능하면 count++
				if (cntA >= constraints[0] && cntC >= constraints[1] &&
						cntG >= constraints[2] && cntT >= constraints[3]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
}
