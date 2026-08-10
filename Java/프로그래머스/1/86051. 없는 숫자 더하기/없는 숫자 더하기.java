class Solution {
    public int solution(int[] numbers) {
       int total = 45;
        
        for(int num : numbers) {
            total -= num;
        }
        
        return total;
    }
}