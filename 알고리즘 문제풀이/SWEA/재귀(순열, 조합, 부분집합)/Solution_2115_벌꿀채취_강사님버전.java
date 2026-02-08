import java.io.*;
import java.util.*;

public class Solution_2115_벌꿀채취 {
	
	static int N, M, C;
	static int[][] map, maxMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// int T = Integer.parseInt(br.readLine());
		
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		maxMap = new int[N][N-M+1];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			st = new StringTokenizer(line);
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = getMaxBenefit();
		System.out.println(result);
	}
	
	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}
	
	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				subset(i, j, 0, 0, 0);
			}
		}
		
	}
	
	private static void subset(int i, int j, int cnt, int sum, int powSum) {
		
		if(sum>C) return;
		
		if (cnt == M) {
			if(maxMap[i][j-M]<powSum) maxMap[i][j-M] = powSum;
			return;
		}
		
		subset(i, j+1, cnt+1, sum+map[i][j], powSum+map[i][j]*map[i][j]);
		
		subset(i, j+1, cnt+1, sum, powSum);
	}
	
	private static int processCombination() {
		int aBenefit, bBenefit, max =0;
		// 일꾼 A 선택
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				aBenefit = maxMap[i][j];
				
				// 일꾼 B 선택
				bBenefit = 0;
				for (int i2 = i; i2 < N; i2++) {
					int start = (i==i2)?j+M : 0;
					for (int j2 = start; j2 <= N-M; j2++) {
						if(bBenefit < maxMap[i2][j2]) {
							bBenefit = maxMap[i2][j2];
						}
					}
				}
				if(max < aBenefit+bBenefit) {
					max = aBenefit+bBenefit;
				}
			}
		}	
			
		return max;
	}

}
