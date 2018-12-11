package reactive;

public class ObservableInterval extends Observable<Long> {
    @Override
    protected void subscribeActual(Observer<? super Long> observer) {
        Thread thread = new Thread() {

            Long i = 0l;

            public void run() {
                while(true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    observer.onNext(i++);
                }

            }
        };

        thread.run();

    }

}
