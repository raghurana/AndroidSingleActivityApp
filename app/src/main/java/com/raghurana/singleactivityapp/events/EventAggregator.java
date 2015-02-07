package com.raghurana.singleactivityapp.events;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class EventAggregator {

    //region Fields
    private static final EventAggregator instance = new EventAggregator();
    private final HashMap<EventType, ArrayList<IEventSubscriber>> EventNameSubscribersMap = new HashMap<EventType, ArrayList<IEventSubscriber>>();
    //endregion

    //region Constructor(s)
    private EventAggregator() {
    }
    //endregion

    //region Public Methods

    public static EventAggregator getInstance() {
        return instance;
    }

    public void publish(final EventType eventCode, final Object publisher) {
        this.publish(eventCode, publisher, null);
    }

    public void publish(final EventType eventCode, final Object publisher, final Bundle data) {
        final ArrayList<IEventSubscriber> subscribers = this.EventNameSubscribersMap.get(eventCode);

        if (subscribers == null) {
            return;
        }

        final int count = subscribers.size();

        // All Subscribers removed (maybe GCed)
        if (count == 0) {
            subscribers.remove(eventCode);
            return;
        }

        for (int i = 0; i < count; i++) {
            final IEventSubscriber subscriber = subscribers.get(i);
            if (subscriber != null) {
                subscriber.onEventTriggered(eventCode, publisher, data);
            }
        }
    }

    public void subscribe(final EventType eventCode, final IEventSubscriber subscriber) {
        ArrayList<IEventSubscriber> subscribers = this.EventNameSubscribersMap.get(eventCode);
        if (subscribers == null) {
            subscribers = new ArrayList<IEventSubscriber>();
            this.EventNameSubscribersMap.put(eventCode, subscribers);
        }

        subscribers.add(subscriber);
    }

    public void unsubscribe(final IEventSubscriber subscriber){
        for(ArrayList<IEventSubscriber> registeredSubs : EventNameSubscribersMap.values()){
            while(registeredSubs.remove(subscriber));
        }
    }

    //endregion
}