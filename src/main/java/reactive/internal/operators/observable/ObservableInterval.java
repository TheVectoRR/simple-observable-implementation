package reactive.internal.operators.observable;

import reactive.Disposable;
import reactive.Observable;
import reactive.Observer;

public class ObservableInterval extends Observable<Long> {

    @Override
    protected void subscribeActual(Observer<? super Long> observer) {
        IntervalObserver d = new IntervalObserver(1000, observer);
        observer.onSubscribe(d);
        d.run();
    }

    static final class IntervalObserver implements Disposable {

        private final Integer period;
        private boolean disposed;
        private boolean isRunning = true;
        private Observer<? super Long> observer;

        IntervalObserver(Integer period, Observer<? super Long> observer) {
            this.period = period;
            this.observer = observer;
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
            Thread thread = new Thread() {
                Long i = 0l;

                public void run() {
                    while(isRunning) {
                        try {
                            sleep(period);
                        } catch (InterruptedException e) {
                            observer.onError(e);
                        }
                        observer.onNext(i++);
                    }
                }
            };
            thread.start();
        }

    }

}
