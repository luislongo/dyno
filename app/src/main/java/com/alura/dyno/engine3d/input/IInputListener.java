package com.alura.dyno.engine3d.input;

import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScaleEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTapEventHandler;

public interface IInputListener {
    void onTap(OnTapEventHandler.OnTapEvent event);
    void onScale(OnScaleEventHandler.OnScaleEvent event);
    void onDrag(OnDragEventHandler.OnDragEvent event);
}
