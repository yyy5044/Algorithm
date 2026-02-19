import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, C;
	static int[][] map, mapMax;
	static int[] num;
	static ArrayList<Box> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			mapMax = new int[N][N-M+1];
			num = new int[M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int i = 0; i < N-M+1; i++) {
					for (int j = i, idx=0; j < M+i;j++) {
						num[idx++] = map[r][j];
					}
					subset(0, 0, 0, r, i);
					list.add(new Box(r, i, mapMax[r][i]));
				}
			}
			
			int maxProfit = Integer.MIN_VALUE;
			for (int i = 0; i < list.size(); i++) {
				for (int j = i+1; j < list.size(); j++) {
					if (list.get(i).r == list.get(j).r) {
						if (list.get(i).c + M <= list.get(j).c) {
							int totalProfit = list.get(i).maxProfit + list.get(j).maxProfit;
							if (totalProfit > maxProfit) {
								maxProfit = totalProfit;
							}
						}
					} else {
						int totalProfit = list.get(i).maxProfit + list.get(j).maxProfit;
						if (totalProfit > maxProfit) {
							maxProfit = totalProfit;
						}
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(maxProfit).append("\n");
			N = M = C = 0;
			list.clear();
		}
		
		System.out.println(sb);
	}
	
	public static void subset(int depth, int sum, int powSum, int r, int c) {
		if (sum>C) return;
		
		if(depth==M) {
			if (mapMax[r][c] < powSum) {
				mapMax[r][c] = powSum;
			}
			return;
		}
		
		subset(depth+1, sum+num[depth], powSum+(num[depth]*num[depth]), r, c);
		subset(depth+1, sum, powSum, r, c);
	}
	
	
}


class Box{
	int r, c, maxProfit;
	public Box(int r, int c, int maxProfit) {
		super();
		this.r = r;
		this.c = c;
		this.maxProfit = maxProfit;
	}
}