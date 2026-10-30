import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;
        int s = 0, e = people.length-1;
        while (s <= e) {
            if (people[e] + people[s] <= limit) {
                s++;
            }
            e--;
            count++;
        }
    
        return count;
    }
}