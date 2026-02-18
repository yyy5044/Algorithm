import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> housePosition = new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						housePosition.add(new int[]{r, c});
					} 
				}
			}
			
			int H = housePosition.size(); // 모든 집 개수
			int ans = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int k = 1; k <= N+1;k++) { // 크기가 k인 마름모 대보기
						int count = 0;
						for (int i = 0; i < H; i++) { // 이 집은 들어가니?
							int[] house = housePosition.get(i);
							if (Math.abs(house[0] - r) + Math.abs(house[1] - c) < k) {
								count++; // 마름모에 집이 들어가면 집 개수 세기
							}
						}
						
						if (count*M >= k*k + ((k-1)*(k-1))) { // 회사가 손해를 안 보면
							ans = Math.max(ans, count); // 답 목록 업데이트
						}
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(ans).append("\n");
			
			housePosition.clear();
		}
		
		System.out.println(sb);
	}
	
	public static int service(int r, int c, int k) {
		int house = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				
			}
		}
		return house;
	}

}

