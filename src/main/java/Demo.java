import reactive.Observable;

public class Demo {
    public static void main(final String[] args) {
        Integer[] numbers = {1, 1, 2, 3, 5, 8, 13, 21, 34};
        Observable<Integer> observable = Observable.fromArray(numbers);
        observable
                .filter((v) -> v % 2 == 0)
                .map((v) -> "an even number : " + v)
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("done!")
                );

        Observable observableInterval = Observable.interval();
        observableInterval.subscribe(System.out::println);
    }
}
