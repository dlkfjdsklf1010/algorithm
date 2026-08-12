class Solution {
    public String solution(int[] food) {
        
        StringBuilder left = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            
            int count = food[i] / 2;
            
            for (int j = 0; j < count; j++) {
                left.append(i);
            }
        }
        
        String right = left.reverse().toString();
        
        left.reverse();
        
        return left + "0" + right;
    }
}