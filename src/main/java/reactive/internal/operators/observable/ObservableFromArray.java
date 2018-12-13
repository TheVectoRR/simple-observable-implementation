package reactive.internal.operators.observable;

import reactive.Disposable;
import reactive.Observable;
import reactive.Observer;

public class ObservableFromArray<T> extends Observable<T> {
    private final T[] array;

    public ObservableFromArray(T[] source) {
        this.array = source;
    }

    @Override
    protected void subscribeActual(final Observer<? super T> observer) {
        FromArrayDisposable<T> d = new FromArrayDisposable<T>(observer, array);

        observer.onSubscribe(d);

        d.run();
    }

    static final class FromArrayDisposable<T> implements Disposable  {

        final Observer<? super T> downstream;

        final T[] array;

        boolean disposed;

        FromArrayDisposable(Observer<? super T> actual, T[] array) {
            this.downstream = actual;
            this.array = array;
        }

        @Override
        public void dispose() {
            disposed = true;
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

        void run() {
            T[] a = array;
            int n = a.length;

            for (int i = 0; i < n && !isDisposed(); i++) {
                T value = a[i];
                if (value == null) {
                    downstream.onError(new NullPointerException("The " + i + "th element is null"));
                    return;
                }
                downstream.onNext(value);
            }
            if (!isDisposed()) {
                downstream.onComplete();
            }
        }
    }
}
