package com.ssafy.algorithm.month2;
import java.io.*;
import java.util.*;

public class Solution_1229_오양호 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 10; t++) {	
			String line = br.readLine(); // 첫 번째 줄
			//System.out.println(line);
			
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			
			line = br.readLine(); // 두 번째 줄
			//System.out.println(line);
			st = new StringTokenizer(line);
			
			LinkedList<String> list = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				list.add(st.nextToken());
			}
			
			
			line = br.readLine(); // 세 번째 줄
			//System.out.println(line);
			int M = Integer.parseInt(line);
			
			line = br.readLine(); // 네 번째 줄
			//System.out.println(line);
			st = new StringTokenizer(line);
			
			for (int i = 0; i < M; i++) {
				String instruct = st.nextToken();
				
				if (instruct.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					String[] s = new String[y];
					for (int j = 0; j < y; j++) {
						s[j] = st.nextToken();
					}
					
					for (int j = 0; j < y; j++) {
						list.add(x + j, s[j]);
					}
				}
				
				else if (instruct.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					for (int j = 0; j < y; j++) {
						if (x == 0) {
							list.remove(0);
						} else {
							list.remove(x);
						}				
					}
				}
			}
			
			sb.append("#" + (t + 1) + " ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i) + " ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
		
	}

}
