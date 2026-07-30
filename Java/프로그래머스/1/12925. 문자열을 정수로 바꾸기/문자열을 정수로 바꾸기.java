class Solution {
    public int solution(String s) {
        int sign = 1;
        int idx = 0;
        
        if(s.charAt(0) == '-') {
            sign = -1;
            idx++;
        } else if (s.charAt(0) == '+') {
            idx++;
        }
        
        int result = 0;
        for (; idx < s.length(); idx++) {
            result = result * 10 + (s.charAt(idx) - '0');
        }
        
        return result * sign;
    }
}