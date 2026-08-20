import java.io.*;
import java.util.*;

// 숙련도: level -> 퍼즐 틀리는 횟수 변화
// 현재 퍼즐의 난이도: diff
// 현재 퍼즐의 소요 시간: time_cur
// 이전 퍼즐의 소요 시간: time_prev
// if (diff <= level): 난이도가 숙련도 이하면 -> time_cur만큼의 시간으로 해결
// else: 난이도가 숙련도보다 높으면 -> diff - level번 틀리게 되고, 퍼즐을 틀릴 때마다 time_cur만큼의 시간을 사용 + time_prev만큼의 시간을 써서 이전 퍼즐 다시 풀고 와야 함. 이전 퍼즐 풀 때는 절대 틀리지 않음.
// diff - level번 틀린 후에는 time_cur만큼 시간 써서 퍼즐 해결.

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length; // 문제 개수
        int max_diff = Integer.MIN_VALUE; // 문제 중 최고 난이도
        for (int i = 0; i < n; i++) {
            max_diff = Math.max(max_diff, diffs[i]);
        }
        
        int min_level = 1; // 최소 숙련도
        
        // 초기값 설정
        int lo = 1;
        int hi = max_diff;
        int mid = (lo + hi) / 2;
        
        while (true) {
            if (lo == mid && hi == mid) {
                min_level = mid;
                break;
            }
            
            if (!canSolveAll(limit, mid, diffs, times, n)) { // 못 풀면
                lo = mid + 1;
                mid = (lo + hi) / 2;
            } else { // 풀면
                hi = mid;
                mid = (lo + hi) / 2; // 오른쪽 버리고 mid 다시 계산
            }
        }
        
        // x x x x o o o
        
        
        return min_level;
    }
    
    static boolean canSolveAll(long limit, int level, int[] diffs, int[] times, int n) {
        boolean flag = false;    
        
        limit = limit - times[0]; // 첫 문제는 푼 시간 차감

        for (int i = 1; i < n; i++) {
            int diff = diffs[i]; // 현재 퍼즐의 난이도
            int time_cur = times[i]; // 현재 퍼즐의 소요시간
            int time_prev = times[i - 1]; // 이전 퍼즐의 소요시간

            if (diff <= level) {
                limit -= time_cur;
            } else {
                limit -= (diff - level)*(time_cur + time_prev) + time_cur;
            }
        }
        
        if (limit >= 0) { // 제한시간 내로 다 풀었다면
            flag = true;
        }
        
        return flag;
    }
}