package Cookies.Search;/* Created by oguzkeremyildiz on 16.05.2020 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class BinarySearch<Symbol> {
    private Comparator<Symbol> comparator;
    public BinarySearch(Comparator<Symbol> comparator) {
        this.comparator = comparator;
    }
    public int search(Symbol array[], int left, int right, Symbol x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (comparator.compare(array[mid], x) == 0) {
                return mid;
            }
            if (comparator.compare(array[mid], x) > 0) {
                return search(array, left, mid - 1, x);
            }
            return search(array, mid + 1, right, x);
        }
        return -1;
    }
    public int search(ArrayList<Symbol> list, int left, int right, Symbol x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (comparator.compare(list.get(mid), x) == 0) {
                return mid;
            }
            if (comparator.compare(list.get(mid), x) > 0) {
                return search(list, left, mid - 1, x);
            }
            return search(list, mid + 1, right, x);
        }
        return -1;
    }
    public int search(LinkedList<Symbol> list, int left, int right, Symbol x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (comparator.compare(list.get(mid), x) == 0) {
                return mid;
            }
            if (comparator.compare(list.get(mid), x) > 0) {
                return search(list, left, mid - 1, x);
            }
            return search(list, mid + 1, right, x);
        }
        return -1;
    }
}
