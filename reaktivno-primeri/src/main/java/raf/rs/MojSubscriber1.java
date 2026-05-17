package raf.rs;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class MojSubscriber1<T> extends BaseSubscriber<T> {
    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(100);
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println("Procitan element "+value);
       // request(100);
    }
}
