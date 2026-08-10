class Solution {
    public String solution(String phone_number) {
        int len = phone_number.length();
        
        String stars = "*".repeat(len - 4);
        String last = phone_number.substring(len - 4);
        
        return stars + last;
    }
}