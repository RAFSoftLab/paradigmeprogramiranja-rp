package raf.rs.primerimoodle;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MojSubscriber<T> implements Subscriber<T> {

    private Subscription s;
    private final int n;

    public MojSubscriber(int n){
        this.n = n;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(n);
    }

    @Override
    public void onNext(T elem) {
        System.out.println(elem);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println("Greska "+t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Kraj");
    }
}
