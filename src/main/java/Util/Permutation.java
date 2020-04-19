package Util;

public class Permutation {

    private int[] a;
    private int n;

    /**
     * A constructor of {@link Permutation} class which creates a new {@link java.lang.reflect.Array} and assigns integer
     * numbers starting from 0 to given input n.
     *
     * @param n integer input.
     */
    public Permutation(int n) {
        int i;
        this.n = n;
        a = new int[n];
        for (i = 0; i < n; i++) {
            a[i] = i;
        }
    }

    /**
     * The get method returns the {@link java.lang.reflect.Array} a.
     *
     * @return Array a.
     */
    public int[] get() {
        return a;
    }

    /**
     * The next method generates next permutation for the {@link java.lang.reflect.Array} a.
     *
     * @return true if next permutation is possible, false otherwise.
     */
    public boolean next() {
        int i = n - 2;
        int j, tmp, k;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false;
        }
        j = n - 1;
        while (a[i] >= a[j]) {
            j--;
        }
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        k = i + 1;
        j = n - 1;
        while (k < j) {
            tmp = a[k];
            a[k] = a[j];
            a[j] = tmp;
            k++;
            j--;
        }
        return true;
    }
}
