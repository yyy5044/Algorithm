import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	
	static class Person {
		int[] pos, dist; // 본인 위치, 계단 1과 2와의 거리
		public Person(int[] pos, int[] dist) {
			super();
			this.pos = pos;
			this.dist = dist;
		}
	}
	
	static class Stair {
	    int r, c, length;
	    public Stair(int r, int c, int length) {
	        this.r = r;
	        this.c = c;
	        this.length = length;
	    }
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			ArrayList<Person> people = new ArrayList<>();
			ArrayList<Stair> stairs = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) people.add(new Person(new int[] {i, j}, new int[2]));
					else if (map[i][j] > 1) stairs.add(new Stair(i, j, map[i][j]));
				}
			}
			
			// 각 계단과의 거리 구하기
			for (int i = 0; i < people.size(); i++) {
				for (int j = 0; j < stairs.size(); j++) {
					people.get(i).dist[j] = Math.abs(stairs.get(j).r - people.get(i).pos[0])
							+ Math.abs(stairs.get(j).c - people.get(i).pos[1]);
				}
			}
			
			int n = people.size();
			int minTotalTime = Integer.MAX_VALUE;
			for (int i = 0; i < (1<<n); i++) {
				ArrayList<Person> s1 = new ArrayList<>();
			    ArrayList<Person> s2 = new ArrayList<>();
				
				for (int j = 0; j < n; j++) {
					if ((i&(1<<j)) != 0) {
						// 1번 계단을 고른 사람들
						s1.add(people.get(j));
					} else {
						// 2번 계단을 고른 사람들
						s2.add(people.get(j));
					}
				}
				
				s1.sort((p1, p2) -> p1.dist[0] - p2.dist[0]); // s1을 dist[0] (1번 계단과의 거리) 기준으로 오름차순 정렬
				s2.sort((p1, p2) -> p1.dist[1] - p2.dist[1]); // s2를 dist[1] (2번 계단과의 거리) 기준으로 오름차순 정렬
				
				// 시뮬레이션 돌리기
			    // stairs.get(0)은 1번 계단(stairIdx=0), stairs.get(1)은 2번 계단(stairIdx=1)
			    int time1 = solve(s1, stairs.get(0), 0);
			    int time2 = solve(s2, stairs.get(1), 1);
			    
			    // 두 그룹 중 더 늦게 끝나는 시간이 이번 조합의 최종 소요 시간
			    int currentTotalTime = Math.max(time1, time2);
			    
			    // 전체 경우의 수 중 가장 짧은 시간으로 갱신
			    minTotalTime = Math.min(minTotalTime, currentTotalTime);
			}
			
			sb.append("#").append(t).append(" ");
			sb.append(minTotalTime).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 시뮬레이션을 돌려 해당 계단을 모두 빠져나가는 최종 시간을 반환하는 함수
	static int solve(ArrayList<Person> list, Stair stair, int stairIdx) {
	    // 이 계단을 이용하는 사람이 아예 없다면 걸린 시간은 0
	    if (list.isEmpty()) return 0; 
	    
	    Queue<Integer> q = new LinkedList<>();
	    int lastFinishTime = 0; // 가장 마지막 사람이 계단을 다 내려온 시간
	    
	    for (Person p : list) { // 큐에 넣는 동시에 시간 계산이 완료되기 때문에 큐를 비울 때까지 반복할 필요 없다
	        int arriveTime = p.dist[stairIdx]; // 계단 입구 도착 시간
	        int canStartTime = arriveTime + 1; // 1분 대기 후, 원칙적으로 진입 가능한 시간
	        
	        if (q.size() < 3) {
	            // 큐(계단)에 자리가 남아 있다면, 대기 직후 바로 진입
	            int finishTime = canStartTime + stair.length;
	            q.offer(finishTime);
	            lastFinishTime = finishTime; // 마지막 사람의 완료 시간 갱신
	        } else {
	            // 계단에 이미 3명이 있어서 꽉 찼다면, 가장 먼저 들어간 사람이 나올 때까지 대기
	            int earliestFinishTime = q.poll(); // 한 명 빼고
	            
	            // "내가 진입 가능한 시간"과 "앞 사람이 빠져나와 자리가 나는 시간" 중 더 늦은 시간에 출발 -> 내가 빨리 왔어도 앞에 나온 사람 시간을 따라가야 함.
	            int actualStartTime = Math.max(canStartTime, earliestFinishTime);
	            int finishTime = actualStartTime + stair.length;
	            
	            q.offer(finishTime);
	            lastFinishTime = finishTime; // 마지막 사람의 완료 시간 갱신
	        }
	    }
	    
	    return lastFinishTime;
	}
}
