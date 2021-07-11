import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        int first = 0;
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int[] t = new int[csa.length()];
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                t[i] = csa.length() - 1;
                first = i;
            }
            else
                t[i] = csa.index(i) - 1;
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < t.length; i++) {
            BinaryStdOut.write(s.charAt(t[i]));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        ArrayList<Character> t = new ArrayList<>();
        while (!BinaryStdIn.isEmpty()) {
            t.add(BinaryStdIn.readChar());
        }
        Character[] firstCol = new Character[t.size()];
        firstCol = t.toArray(firstCol);
        Arrays.sort(firstCol);
        // for (int i=0;i<firstCol.length;i++)
        //     System.out.print(firstCol[i]);
        // System.out.println();
        // for (int i=0;i<t.size();i++)
        //     System.out.print(t.get(i));
        // System.out.println(first);
        int[] next = new int[firstCol.length];
        boolean[] mark = new boolean[firstCol.length];
        for (int i = 0; i < firstCol.length; i++) {
            for (int j = 0; j < t.size(); j++) {
                if (!mark[j] && firstCol[i].equals(t.get(j))) {
                    next[i] = j;
                    mark[j] = true;
                    break;
                }
                else if (j == t.size() - 1) {
                    next[i] = first;
                    mark[first]=true;
                }
            }
        }
        // for (int i = 0; i < t.size();i++)
        //     System.out.print(next[i]);
        // System.out.println();
        int cur = first;
        // do {
        //     BinaryStdOut.write(firstCol[cur]);
        //     cur = next[cur];
        // } while (cur != first);
        for (int i =0;i<firstCol.length;i++){
            BinaryStdOut.write(firstCol[cur]);
            cur=next[cur];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else
            throw new IllegalArgumentException("input must be '-' or '+'!");
    }
}
