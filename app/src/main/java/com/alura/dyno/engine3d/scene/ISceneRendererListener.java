package com.alura.dyno.engine3d.scene;

import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewCreatedEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewChangedHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewCreatedHandler;

public interface ISceneRendererListener {
        void onViewCreated(OnViewCreatedEvent event);
        void onViewChanged(OnViewChangedEvent event);
        void onRender(OnRenderEvent event);
}
