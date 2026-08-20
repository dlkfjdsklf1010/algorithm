class Solution {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = 4_000_000_000_000_000L;

        while (left < right) {
            long mid = (left + right) / 2;

            if (canTransport(mid, a, b, g, s, w, t)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canTransport(
            long time,
            int a,
            int b,
            int[] g,
            int[] s,
            int[] w,
            int[] t
    ) {
        long gold = 0;
        long silver = 0;
        long total = 0;

        for (int i = 0; i < g.length; i++) {
            long roundTrip = 2L * t[i];

            long trips = time / roundTrip;

            if (time % roundTrip >= t[i]) {
                trips++;
            }

            long capacity = trips * w[i];

            long goldTransport = Math.min((long) g[i], capacity);
            long silverTransport = Math.min((long) s[i], capacity);

            gold += goldTransport;
            silver += silverTransport;

            long totalTransport = Math.min(
                    (long) g[i] + s[i],
                    capacity
            );

            total += totalTransport;
        }

        return gold >= a
                && silver >= b
                && total >= (long) a + b;
    }
}