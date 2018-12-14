package reactive.internal.operators.observable;

import reactive.Disposable;
import reactive.Observable;
import reactive.Observer;

public class ObservableInterval extends Observable<Long> {

    private static boolean isRunning = true;

    @Override
    protected void subscribeActual(Observer<? super Long> observer) {
        Thread thread = new Thread() {

            Long i = 0l;

            public void run() {
                while(isRunning) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        observer.onError(e);
                    }
                    observer.onNext(i++);
                }

            }
        };

        IntervalObserver d = new IntervalObserver(thread);

        observer.onSubscribe(d);

        d.run();

    }

    static final class IntervalObserver implements Disposable {

        final Thread thread;

        boolean disposed;

        IntervalObserver(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void dispose() {
            isRunning = false;
            disposed = true;
        }

        @Override
        public boolean isDisposed() {
            return disposed;
        }

        public void run() {
            thread.start();
        }

    }

}
