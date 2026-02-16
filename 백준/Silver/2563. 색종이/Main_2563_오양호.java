import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 흰 도화지 준비
		BitSet[] white = new BitSet[100];
		for (int i = 0; i < 100; i++) {
			white[i] = new BitSet(100);
		}
		
		// 입력
		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}

			int x = input[i][0];
			int y = input[i][1];
			for (int j = y; j < y+10; j++) {
				white[j].set(x, x+10);
			}
		}
		
		int totalCnt = 0;
		
		for (int i = 0; i < 100; i++) {
			totalCnt += white[i].cardinality();
		}
		
		System.out.println(totalCnt);
		
	}


}
