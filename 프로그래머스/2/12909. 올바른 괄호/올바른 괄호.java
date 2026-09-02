class Solution {
    boolean solution(String s) {
        
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '(') sum++;
            else sum--;
            
            if (sum < 0) return false;
        }
        
        if (sum == 0) return true;
        else return false;
    
    }
}