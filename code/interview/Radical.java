package code.interview;

/**
 * @decreption: 求根号2的值，保留10位小数；根号2值是：1.414213562373...
 */
public class Radical {
    public static void main(String[] args) {
        double r = 1;
        double denom = 10;
        for (int i = 1; i <= 10; i++) {
            r = getNum(r, denom, 0, 9);
            denom*=10;
        }
        System.out.println(r);
    }

    private static double getNum(double base, double denom, int l, int r) {
        int mid = l + ((r - l) >> 1);
        double cur = base + (double) mid / denom;
        double next = base + (double) (mid + 1) / denom;
        double cur2 = cur * cur;
        double next2 = next * next;
        if (cur2 < 2 && next2 > 2) {
            return cur;
        } else if (cur2 > 2) {
            return getNum(base, denom, l, mid - 1);
        } else {
            return getNum(base, denom, mid + 1, r);
        }

    }
}
