import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
		
		for (int t =0; t < T; t++) {
			String line = br.readLine(); // 첫 번째 줄, N, M
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			
			line = br.readLine(); // 두 번재 줄, 과자 봉지 무게
			st = new StringTokenizer(line);
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			
			// 투 포인터
			int s = 0, e = N - 1, sum = 0,target = M, max = -1;
			
			Arrays.sort(arr); // 먼저 정렬
			
			while(s < e) {
				sum = arr[s] + arr[e];
				if (sum > target) {
					e--;
				} else if (sum == target) {
					max = sum;
					break;
				} else {
					if(max < sum) max = sum;
					s++;
				}
			}
			
			sb.append("#"+(t+1)+" "+max).append("\n");
		}
		System.out.println(sb);
	}

}
