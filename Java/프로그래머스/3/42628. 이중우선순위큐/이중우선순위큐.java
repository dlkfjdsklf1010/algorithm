import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            String command = parts[0];

            if (command.equals("I")) {
                int num = Integer.parseInt(parts[1]);
                minHeap.offer(num);
                maxHeap.offer(num);
            } else {
                if (minHeap.isEmpty()) {
                    continue;
                }

                if (parts[1].equals("1")) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } else {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }

        if (minHeap.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{
            maxHeap.peek(),
            minHeap.peek()
        };
    }
}