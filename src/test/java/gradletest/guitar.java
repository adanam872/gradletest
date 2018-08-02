package gradletest;

public class guitar {

    public int leastBorders(int[] X, int[] Y, int[] R, int x1, int y1, int x2, int y2) {
    
        int ret = 0;
        for (int i = 0; i < X.length; i++) {
            if ((X[i] - x1) * (X[i] - x1) + (Y[i] - y1) * (Y[i] - y1) < R[i] * R[i]
                    && (X[i] - x2) * (X[i] - x2) + (Y[i] - y2) * (Y[i] - y2) > R[i] * R[i]
                    || (X[i] - x1) * (X[i] - x1) + (Y[i] - y1) * (Y[i] - y1) > R[i] * R[i]
                            && (X[i] - x2) * (X[i] - x2) + (Y[i] - y2) * (Y[i] - y2) < R[i] * R[i]) {
                ret++;
            }
        }
    
        return ret;
    }

    public static void main(String[] args) {
//        int[] X = { 0, -6, 6 };
//        int[] Y = { 0, 1, 2 };
//        int[] R = { 2, 2, 2 };
//        int x1 = -5;
//        int y1 = 1;
//        int x2 = 5;
//        int y2 = 1;

        int[] X = { 1, -3, 2, 5, -4, 12, 12 };
        int[] Y = { 1, -1, 2, 5, 5, 1, 1 };
        int[] R = { 8, 1, 2, 1, 1, 1, 2 };
        int x1 = -5;
        int y1 = 1;
        int x2 = 12;
        int y2 = 1;
        System.out.println(new guitar().leastBorders(X, Y, R, x1, y1, x2, y2));
    }
}
