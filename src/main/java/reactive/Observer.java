package reactive;

public interface Observer <T>{
    void onNext(T t);

    void onError(Throwable e);

    void onComplete();
}
