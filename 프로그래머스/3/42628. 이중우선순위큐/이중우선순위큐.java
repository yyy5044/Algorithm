import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        
        for (String s : operations) {
            char instruct = s.charAt(0);
            String number_str = s.substring(2, s.length());
            int number = 0;
            if (number_str.charAt(0) == '-') {
                number = (-1) * Integer.parseInt(number_str.substring(1, number_str.length()));
            } else {
                number = Integer.parseInt(number_str); 
            }

            if (instruct == 'I') {
                max_heap.add(number);
                min_heap.add(number);
            } else if (instruct == 'D') {
                char tmp = s.charAt(2);
                
                if (tmp == '1' && !max_heap.isEmpty()) {
                    int num = max_heap.poll();
                    min_heap.remove(num);
                } else if (tmp == '-' && !min_heap.isEmpty()) {
                    int num = min_heap.poll();
                    max_heap.remove(num);
                }
            }
        }
        
        int[] answer = new int[2];
        if (max_heap.peek() != null) {
            answer[0] = max_heap.poll();
        } else {
            answer[0] = 0;
        }
        
        if (min_heap.peek() != null) {
            answer[1] = min_heap.poll();
        } else {
            answer[1] = 0;
        }
        
        
        return answer;
    }
}