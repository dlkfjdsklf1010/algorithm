class Solution {
    public String solution(int a, int b) {
        
        String[] days = {
            "SUN", "MON", "TUE", "WED",
            "THU", "FRI", "SAT"
        };
        
        int[] month = {
            31, 29, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        
        int total = 0;
        
        for (int i = 0; i < a - 1; i++) {
            total += month[i];
        }
        
        total += b;
        
        return days[(total + 4) % 7];
    }
}