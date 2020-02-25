package com.demo.gateway.publisher;

import com.demo.gateway.model.graph.card.CardItem;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationPublisher {
    public static final ConcurrentLinkedQueue<CardItem> NOTE_QUEUE = new ConcurrentLinkedQueue<>();
    private final Flowable<CardItem> publisher;

    public NotificationPublisher() {
        Observable<CardItem> notificationObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newNote(emitter), 0, 1, TimeUnit.SECONDS);

        });

        ConnectableObservable<CardItem> connectableObservable = notificationObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.DROP);
    }

    private Runnable newNote(ObservableEmitter<CardItem> emitter) {
        return () -> {
            CardItem card = NOTE_QUEUE.poll();
            if (card != null){
                emitter.onNext(card);
            }
        };
    }

    public Flowable<CardItem> getNotification() {
        return publisher;
    }

}
