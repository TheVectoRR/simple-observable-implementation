package reactive.observable.operators;

import reactive.Observable;
import reactive.Observer;

import java.util.function.Function;

public class ObservableMap<T, R> extends Observable {

    private final Observable<T> source;
    private final Function<? super T, ? extends R> mapper;

    public <R, T> ObservableMap(
            final Observable observable,
            final Function mapper
    ) {
        this.source = observable;
        this.mapper = mapper;

    }

    @Override
    protected void subscribeActual(final Observer observer) {
        source.subscribe((v) -> observer.onNext(mapper.apply(v)));
    }
}
