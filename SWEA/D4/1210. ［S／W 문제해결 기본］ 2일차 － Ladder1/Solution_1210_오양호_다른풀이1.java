import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] map = new int[100][100];
		
		for (int t = 0; t < 10; t++) {
			int T = Integer.parseInt(br.readLine());
			
			int end = 0;
			for(int r = 0; r < 100; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 100; c++){
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[99][c] == 2) end = c; 
				}
			}
			
			int x = end;
			for (int h = 99; h >= 0; h--) {
				if (x-1>0 && map[h][x-1] == 1) { // 왼쪽 확인
					// 왼쪽으로 쭉 이동
					while(x-1>0 && map[h][x-1] == 1) x--;
				} else if (x+1<=99 && map[h][x+1] == 1) { // 오른쪽 확인
					// 오른쪽으로 쭉 이동
					while(x+1<=99 && map[h][x+1] == 1) x++;
				} else { // 안 되면 위로
					// 위로 한 칸 이동
				}
			}
			
			sb.append("#").append(T).append(" ")
				.append(x).append("\n");
		}

		System.out.println(sb);
	}

}
