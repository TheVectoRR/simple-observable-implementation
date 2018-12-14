package reactive.internal;

import reactive.Disposable;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * In RXJava2 this class is thread safe, all operations are atomically,
 * Also, it doesn't use a UUID
 */
public class DisposableHelper {

    private static final Map<UUID, Disposable> resourcesMap = new TreeMap<>();

    private DisposableHelper(){}

    public static <T> void setOnce(LambdaObserver<T> obs, Disposable d) {
        resourcesMap.put(obs.getUuid(), d);
    }

    public static <T> void dispose(LambdaObserver<T> obs) {
        resourcesMap.get(obs.getUuid()).dispose();
    }
}
