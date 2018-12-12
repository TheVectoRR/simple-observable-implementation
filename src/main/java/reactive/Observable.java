package reactive;

import reactive.internal.LambdaObserver;
import reactive.internal.operators.ObservableFilter;
import reactive.internal.operators.ObservableMap;
import reactive.internal.operators.observable.ObservableFromArray;
import reactive.internal.operators.observable.ObservableInterval;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Observable <T>{
    public static <T> ObservableFromArray<T> fromArray(T... items) {
        return new ObservableFromArray<>(Arrays.asList(items));
    }

    public static ObservableInterval interval() {
        return new ObservableInterval();
    }

    public Disposable subscribe(
            Consumer<? super T> onNext,
            Consumer<? super Throwable> onError,
            Action onComplete
    ){
        var ls = new LambdaObserver<T>(onNext, onError, onComplete);
        subscribeActual(ls);
        return ls;
    }

    public Disposable subscribe(Consumer<? super T> onNext) {
        return subscribe(onNext, e -> {}, () -> {});
    }

    public Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        return subscribe(onNext, onError, () -> {});
    }

    public final Observable<T> filter(final Predicate<? super T> predicate) {
        return new ObservableFilter<>(this, predicate);
    }

    public final <R> Observable<R> map(final Function<? super T, ? extends R> mapper) {
        return new ObservableMap<>(this, mapper);
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

}
