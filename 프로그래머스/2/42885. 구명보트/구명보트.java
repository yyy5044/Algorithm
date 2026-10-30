import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;
        int s = 0, e = people.length-1;
        while (s <= e) {
            int heavy = people[e];
            int light = people[s];
            if (heavy + light > limit) {
                e--; // 무거운 애 태워서 보트 보내고
                count++; // 카운트 추가
            } else {
                s++;
                e--;
                count++;
            }
        }
    
        return count;
    }
}