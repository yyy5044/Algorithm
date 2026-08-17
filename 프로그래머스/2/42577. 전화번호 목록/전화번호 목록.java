import java.io.*;
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        for (String phoneNumber : phone_book) {
            set.add(phoneNumber);
        }
        
        for (String phoneNumber : phone_book) {
            for (int i = 1; i < phoneNumber.length(); i++) {
                if (set.contains(phoneNumber.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}