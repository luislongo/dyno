package com.alura.dyno.engine3d.eventsystem.events;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.math.graphics.Vector2;

public class OnViewChangedEvent implements IEvent {
        Vector2 screenSize;

        public OnViewChangedEvent(int width, int height) {
            screenSize = new Vector2(width, height);
        }
        @Override
        public TreeEventType getType() {
            return TreeEventType.OnViewChanged;
        }
        public int width() {
            return (int) screenSize.x();
        }
        public int height() {
            return (int) screenSize.y();
        }
        public Vector2 screenSize() {return screenSize.clone();}
    }


