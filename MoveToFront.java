import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private final static int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        char[] charOrder = new char[R];
        for (char c = 0; c < R; c++)
            charOrder[c] = c;

        int k = 0;
        for (char c : input) {
            for (int i = 0; i < R; i++) {
                if (charOrder[i] == c) {
                    k = i;
                    break;
                }
            }
            BinaryStdOut.write(k, 8);
            for (int i = k - 1; i >= 0; i--) {
                charOrder[i + 1] = charOrder[i];
            }
            charOrder[0] = c;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char n ;
        char[] charOrder = new char[R];
        for (char c = 0; c < R; c++)
            charOrder[c] = c;
        char w;
        while (!BinaryStdIn.isEmpty()){
            n=BinaryStdIn.readChar(8);
            w=charOrder[n];
            BinaryStdOut.write(w, 8);
            for (int i = n - 1; i >= 0; i--) {
                charOrder[i + 1] = charOrder[i];
            }
            charOrder[0] = w;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else
            throw new IllegalArgumentException("input must be '-' or '+'!");

    }
}