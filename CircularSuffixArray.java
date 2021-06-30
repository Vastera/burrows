import edu.princeton.cs.algs4.In;

public class CircularSuffixArray {
    private final String originalArray;
    private int[] order;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("input string is null~");
        originalArray = s;
        order = new int[length()];
        for (int i = 0; i < length(); i++)
            order[i] = i;
        sort(0, length() - 1, 0);
    }

    private void sort(int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        char v = csArray(lo, d);
        int i = lo + 1;
        while (i <= gt) {
            char t = csArray(i, d);
            if (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else i++;
        }
        sort(lo, lt - 1, d);
        if (v >= 0) sort(lt, gt, d + 1);
        sort(gt + 1, hi, d);
    }

    private void exch(int i, int j) {
        int t = order[i];
        order[i] = order[j];
        order[j] = t;
    }

    private char csArray(int i, int index) {
        int offset = order[i];
        if (offset < 0 || offset >= length())
            throw new IllegalArgumentException("offset is not in range [0, " + length() + "]!");
        if (index < 0 || index >= length())
            throw new IllegalArgumentException(
                    "index=" + index + " is not in range [0, " + length() + "]!");
        if (index + offset >= length())
            return originalArray.charAt(offset + index - length());
        else
            return originalArray.charAt(offset + index);
    }

    // length of s
    public int length() {
        return originalArray.length();
    }

    // return index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length() - 1)
            throw new IllegalArgumentException("index must be in range [0 " + length() + "]!");
        return order[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);
        String s = in.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        System.out.println("length= " + csa.length() + "\n");
        for (int i = 0; i < csa.length(); i++)
            System.out.print(csa.index(i) + " ");
    }
}
