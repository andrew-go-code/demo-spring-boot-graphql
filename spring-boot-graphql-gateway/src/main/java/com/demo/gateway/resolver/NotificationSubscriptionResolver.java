package com.demo.gateway.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.demo.gateway.model.graph.card.CardItem;
import com.demo.gateway.publisher.NotificationPublisher;
import com.demo.gateway.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class NotificationSubscriptionResolver implements GraphQLSubscriptionResolver {
    private final NotificationPublisher notificationPublisher;

    @PermitAll
    public Publisher<CardItem> card(){
        return notificationPublisher.getNotification();
    }
}