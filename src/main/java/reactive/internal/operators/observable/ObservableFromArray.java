package reactive.internal.operators.observable;

import reactive.Observable;
import reactive.Observer;

import java.util.List;

public class ObservableFromArray<T> extends Observable<T> {
    private List<T> source;

    public ObservableFromArray(final List<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(final Observer<? super T> observer) {
        source.stream()
                .forEach(v -> observer.onNext(v));
    }
}
