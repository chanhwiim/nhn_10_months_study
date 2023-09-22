@FunctionalInterface
public interface BinaryOp {
    public int apply(int i, int j);
}

class Adder implements BinaryOp {
    public int apply(int i, int j) {
        return i + j;
    }
}

class Multi implements BinaryOp {
    public int apply(int i, int j) {
        return i * j;
    }
}

class Algo {
    public static int Calc(BinaryOp binaryOp, int i, int j) {
        return binaryOp.apply(i, j);
    }

    static BinaryOp adder = new BinaryOp() {
        public int apply(int i, int j) {
            return i + j;
        }
    };

    public static void main(String[] args) {
        Calc((a, b) -> a % b, 1, 3);
    }
}