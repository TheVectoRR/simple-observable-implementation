package reactive.internal;

import reactive.Disposable;

/**
 * In RXJava2 this class is thread safe, all operations are atomically
 */
public class DisposableHelper {

    // TODO
    public static <T> void setOnce(LambdaObserver<T> tLambdaObserver, Disposable d) {
        System.out.println("SetResource");
    }

    // TODO
    public static <T> void dispose(LambdaObserver<T> tLambdaObserver) {
        System.out.println("disposeResource");
    }
}
