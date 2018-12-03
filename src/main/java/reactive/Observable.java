package reactive;

import java.util.Arrays;
import java.util.function.Consumer;

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

    protected abstract void subscribeActual(Observer<? super T> myObserver);

}
