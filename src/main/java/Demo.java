import reactive.Observable;

public class Demo {
    public static void main(final String[] args) {
        Integer[] numbers = {1, 1, 2, 3, 5, 8, 13, 21, 34};
        Observable<Integer> observable = Observable.fromArray(numbers);
        observable
                .subscribe(System.out::println);
    }
}
