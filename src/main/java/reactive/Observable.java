package reactive;

import reactive.observable.operators.ObservableFilter;
import reactive.observable.operators.ObservableMap;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Observable <T>{
    public static <T> ObservableFromArray<T> fromArray(T... items) {
        return new ObservableFromArray<>(Arrays.asList(items));
    }

    public void subscribe(final Observer<? super T> observer){
        subscribeActual(observer);
    }

    public void subscribe(Consumer<? super T> consumer) {
        subscribe(new Observer<>() {
            @Override
            public void onNext(T t) {
                consumer.accept(t);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public final Observable<T> filter(final Predicate<? super T> predicate) {
        return new ObservableFilter<>(this, predicate);
    }

    public final <R> Observable<R> map(final Function<? super T, ? extends R> mapper) {
        return new ObservableMap<>(this, mapper);
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

}
