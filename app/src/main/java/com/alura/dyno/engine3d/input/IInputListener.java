package com.alura.dyno.engine3d.input;

import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnScaleEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnTapEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTapEventHandler;

public interface IInputListener {
    void onTap(OnTapEvent event);
    void onScale(OnScaleEvent event);
    void onDrag(OnDragEvent event);
}
