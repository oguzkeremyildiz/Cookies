package Cookies.Graph;/* Created by oguzkeremyildiz on 18.05.2020 */

public class DoubleLength implements LengthInterface<Double>{
    @Override
    public int compare(Double length1, Double length2) {
        return Double.compare(length1, length2);
    }

    @Override
    public Double min() {
        return 0.0;
    }

    @Override
    public Double max() {
        return Double.MAX_VALUE;
    }

    @Override
    public Double add(Double length1, Double length2) {
        return length1 + length2;
    }
}
