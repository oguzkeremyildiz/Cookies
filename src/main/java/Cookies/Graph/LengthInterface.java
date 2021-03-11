package Cookies.Graph;

public interface LengthInterface<Length> {
    int compare(Length length1, Length length2);
    Length min();
    Length max();
    Length add(Length length1, Length length2);
    Length remove(Length length1, Length length2);
    Length min(Length length1, Length length2);
}
