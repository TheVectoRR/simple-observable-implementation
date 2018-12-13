package reactive;

public interface Observer <T>{

    // Provides the Observer with the means of cancelling
    void onSubscribe(Disposable d);

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();
}
