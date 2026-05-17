package raf.rs;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public class MojSubscriber<T> implements Subscriber<T> {

    private Subscription s;

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        s.request(1);
    }

    @Override
    public void onNext(T elem) {
        System.out.println("Procitan element "+elem);
        s.request(1);


    }

    @Override
    public void onError(Throwable t) {
        System.err.println("Greska "+t.getMessage());
        s.request(1);
    }

    @Override
    public void onComplete() {
        System.out.println("Kraj!!!!");
    }
}
