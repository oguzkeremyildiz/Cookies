package Cookies.Sort;/* Created by oguzkeremyildiz on 12.04.2020 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author oguzkeremyildiz
 * @version 1.0.0
 */

public class QuickSort<Symbol>  {
    private Comparator<Symbol> comparator;
    public QuickSort(Comparator<Symbol> comparator) {
        this.comparator = comparator;
    }
    private void swap(ArrayList<Symbol> list, int i, int j){
        Symbol tmp;
        tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
    private int partition(ArrayList<Symbol> list, int first, int last){
        Symbol x = list.get(last);
        int i = first - 1, j;
        for (j = first; j < last; j++){
            if (comparator.compare(list.get(j), x) < 0){
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, last);
        return i + 1;
    }
    public void sort(ArrayList<Symbol> list, int first, int last){
        int pivot;
        if (first < last){
            pivot = partition(list, first, last);
            sort(list, first, pivot - 1);
            sort(list, pivot + 1, last);
        }
    }

    private void swap(LinkedList<Symbol> list, int i, int j){
        Symbol tmp;
        tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
    private int partition(LinkedList<Symbol> list, int first, int last){
        Symbol x = list.get(last);
        int i = first - 1, j;
        for (j = first; j < last; j++){
            if (comparator.compare(list.get(j), x) < 0){
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, last);
        return i + 1;
    }
    public void sort(LinkedList<Symbol> list, int first, int last){
        int pivot;
        if (first < last){
            pivot = partition(list, first, last);
            sort(list, first, pivot - 1);
            sort(list, pivot + 1, last);
        }
    }
}
