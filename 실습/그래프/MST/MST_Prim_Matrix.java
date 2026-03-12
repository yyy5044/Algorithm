import java.io.*;
import java.util.*;

public class MST_Prim_Matrix {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 기초 정보 설정
        int V = Integer.parseInt(br.readLine()); // 정점의 개수
        int[][] adjMatrix = new int[V][V];       // 정점 간의 연결 상태와 비용(가중치)을 저장하는 지도
        boolean[] visited = new boolean[V];      // 각 정점이 'MST 팀'에 합류했는지 체크하는 체크리스트
        int[] minEdge = new int[V];              // 각 정점이 현재 'MST 팀'과 연결될 수 있는 가장 저렴한 비용 저장소

        // 2. 그래프 정보(인접 행렬) 입력 받기
        StringTokenizer st = null;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 3. 초기화 (전처리)
        // 모든 정점의 최소 비용을 일단 '무한대'로 설정 (아직 아무것도 연결 안 됐으니까요)
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        
        // 시작점(0번 정점)을 정해줍니다. '나 자신'으로의 거리는 0이므로 0으로 시작!
        // 이렇게 해야 첫 번째 반복문에서 0번 정점이 선택됩니다.
        minEdge[0] = 0;
        
        int result = 0; // 최종적으로 만들어진 MST의 총 비용 합계
        int c;          // 정점을 몇 개나 팀에 합류시켰는지 세는 카운터

        // 4. 알고리즘 본체 (V개의 정점을 하나씩 팀에 합류시키기 위해 V번 반복)
        for (c = 0; c < V; c++) {
            
            // [Step 1] 아직 팀원이 아닌(non-visited) 정점 중, '가장 저렴하게' 팀에 들어올 수 있는 정점 찾기
            int min = Integer.MAX_VALUE; // 이번 턴에서 가장 작은 비용을 저장할 변수
            int minVertex = -1;          // 그 비용을 가진 정점의 번호
            
            for (int i = 0; i < V; i++) {
                // 팀원이 아니고(&&), 현재 가진 비용(minEdge)이 지금까지 찾은 최소값(min)보다 작다면?
                if (!visited[i] && min > minEdge[i]) {
                    minVertex = i;       // 너가 이번에 우리 팀에 들어올 '최소 비용 정점'이구나!
                    min = minEdge[i];
                }
            }
            
            // 만약 더 이상 가져올 정점이 없다면(연결 끊김) 중단
            if (minVertex == -1) break; 
            
            // [Step 2] 선발된 정점을 'MST 팀'에 공식 합류시키기
            visited[minVertex] = true;   // 팀 합류 완료 체크!
            result += min;               // 팀 가입 비용을 총합에 더하기
            
            // [Step 3] 방금 들어온 '새 멤버' 덕분에 다른 정점들이 팀에 더 싸게 들어올 수 있는지 확인 (업데이트)
            for (int i = 0; i < V; i++) {
                // 1. 아직 팀원이 아니고(!visited)
                // 2. 새 멤버(minVertex)와 연결되어 있으며(adjMatrix != 0)
                // 3. 새 멤버를 거쳐서 오는 비용이 기존에 알고 있던 최소 비용(minEdge)보다 작다면?
                if (!visited[i] && adjMatrix[minVertex][i] != 0 
                        && minEdge[i] > adjMatrix[minVertex][i]) {
                    
                    // 더 싼 경로를 찾았으니 정보를 업데이트해줍니다.
                    minEdge[i] = adjMatrix[minVertex][i]; 
                }
            }
        }
        
        // 모든 정점이 팀에 합류했다면(c == V) 총 비용 출력, 아니면 연결 실패(-1)
        System.out.println( c == V ? result : -1);
    }
}