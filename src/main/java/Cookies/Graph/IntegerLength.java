package Cookies.Graph;/* Created by oguzkeremyildiz on 18.05.2020 */

public class IntegerLength implements LengthInterface<Integer>{
    @Override
    public int compare(Integer length1, Integer length2) {
        return Integer.compare(length1, length2);
    }

    @Override
    public Integer min() {
        return 0;
    }

    @Override
    public Integer max() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Integer add(Integer length1, Integer length2) {
        return length1 + length2;
    }

    @Override
    public Integer remove(Integer length1, Integer length2) {
        return length1 - length2;
    }
}
