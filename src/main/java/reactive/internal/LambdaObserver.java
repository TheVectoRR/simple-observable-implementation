package reactive.internal;

import reactive.Action;
import reactive.Disposable;
import reactive.Observer;

import java.util.UUID;
import java.util.function.Consumer;

public class LambdaObserver<T> implements Observer<T>, Disposable {

    private Consumer<? super T> onNext;
    private Consumer<? super Throwable> onError;
    private Action onComplete;
    private boolean isDisposed = false;
    private UUID uuid; // not in RXJava2

    public LambdaObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {
        this.onNext = onNext;
        this.onError = onError;
        this.onComplete = onComplete;
        uuid = UUID.randomUUID();
    }

    @Override
    public void onSubscribe(Disposable d) {
        // is an atomic operation in RXJava2
        DisposableHelper.setOnce(this, d);
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

    @Override
    public void dispose() {
        // is an atomic operation in RXJava2
        DisposableHelper.dispose(this);
        isDisposed = true;
    }

    @Override
    public boolean isDisposed() {
        return isDisposed;
    }

    public UUID getUuid() {
        return uuid;
    }
}
