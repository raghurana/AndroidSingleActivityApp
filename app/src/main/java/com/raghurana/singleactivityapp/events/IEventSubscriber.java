package com.raghurana.singleactivityapp.events;

import android.os.Bundle;

public interface IEventSubscriber {

    public void onEventTriggered(final EventType eventCode, final Object publisher, final Bundle data);

}
