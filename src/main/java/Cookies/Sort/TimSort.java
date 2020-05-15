package Cookies.Sort;/* Created by oguzkeremyildiz on 14.05.2020 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class TimSort<Symbol> {
    private Comparator<Symbol> comparator;
    public TimSort(Comparator<Symbol> comparator) {
        this.comparator = comparator;
    }
    private void insertionSort(LinkedList<Symbol> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Symbol temp = list.get(i);
            int j = i - 1;
            while (j >= left && comparator.compare(list.get(j), temp) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }
    private void merge(LinkedList<Symbol> list, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        LinkedList<Symbol> left = new LinkedList<>();
        LinkedList<Symbol> right = new LinkedList<>();
        for (int x = 0; x < len1; x++) {
            left.set(x, list.get(l + x));
        }
        for (int x = 0; x < len2; x++) {
            right.set(x, list.get(m + 1 + x));
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2) {
            if (comparator.compare(left.get(i), right.get(j)) == 0 || comparator.compare(right.get(j), left.get(i)) > 0) {
                list.set(k, left.get(i));
                i++;
            } else {
                list.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < len1) {
            list.set(k, left.get(i));
            k++;
            i++;
        }
        while (j < len2) {
            list.set(k, right.get(j));
            k++;
            j++;
        }
    }
    public void sort(LinkedList<Symbol> arr, int length) {
        for (int i = 0; i < length; i += 32) {
            insertionSort(arr, i, Math.min((i + 31), (length - 1)));
        }
        for (int size = 32; size < length; size = 2 * size) {
            for (int left = 0; left < length; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (length - 1));
                merge(arr, left, mid, right);
            }
        }
    }
    private void insertionSort(ArrayList<Symbol> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Symbol temp = list.get(i);
            int j = i - 1;
            while (j >= left && comparator.compare(list.get(j), temp) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, temp);
        }
    }
    private void merge(ArrayList<Symbol> list, int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        ArrayList<Symbol> left = new ArrayList<>();
        ArrayList<Symbol> right = new ArrayList<>();
        for (int x = 0; x < len1; x++) {
            left.set(x, list.get(l + x));
        }
        for (int x = 0; x < len2; x++) {
            right.set(x, list.get(m + 1 + x));
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < len1 && j < len2) {
            if (comparator.compare(left.get(i), right.get(j)) == 0 || comparator.compare(right.get(j), left.get(i)) > 0) {
                list.set(k, left.get(i));
                i++;
            } else {
                list.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < len1) {
            list.set(k, left.get(i));
            k++;
            i++;
        }
        while (j < len2) {
            list.set(k, right.get(j));
            k++;
            j++;
        }
    }
    public void sort(ArrayList<Symbol> arr, int length) {
        for (int i = 0; i < length; i += 32) {
            insertionSort(arr, i, Math.min((i + 31), (length - 1)));
        }
        for (int size = 32; size < length; size = 2 * size) {
            for (int left = 0; left < length; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (length - 1));
                merge(arr, left, mid, right);
            }
        }
    }
}
