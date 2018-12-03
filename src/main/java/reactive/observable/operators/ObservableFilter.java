package reactive.observable.operators;

import reactive.Observable;
import reactive.Observer;

import java.util.function.Predicate;

public class ObservableFilter <T> extends Observable {

    private final Observable<T> source;
    private final Predicate<? super T> predicate;

    public <T> ObservableFilter(
            final Observable Observable,
            final Predicate predicate
    ) {
        this.source = Observable;
        this.predicate = predicate;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribe((v)->{
            if (predicate.test(v)) {
                observer.onNext(v);
            }
        });
    }
}
