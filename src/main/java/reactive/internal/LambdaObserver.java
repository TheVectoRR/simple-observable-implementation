package reactive.internal;

import reactive.Action;
import reactive.Disposable;
import reactive.Observer;

import java.util.function.Consumer;

public class LambdaObserver<T> implements Observer<T>, Disposable {

    private Consumer<? super T> onNext;
    private Consumer<? super Throwable> onError;
    private Action onComplete;
    private boolean isDisposed = false;

    public LambdaObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
    }

    @Override
    public void onNext(T t) {
        if (!isDisposed()) {
            onNext.accept(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!isDisposed()) {
            onError.accept(e);
        }
    }

    @Override
    public void onComplete() {
        if (!isDisposed()) {
            onComplete.run();
        }
    }

    // is an atomic operation in RXJava2
    @Override
    public void dispose() {
        isDisposed = true;
    }

    // reads the state as a singleton instance in RXJava2
    @Override
    public boolean isDisposed() {
        return isDisposed;
    }
}
