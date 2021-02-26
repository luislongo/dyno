package com.alura.dyno.engine3d.script;

import android.provider.Settings;

import androidx.annotation.TransitionRes;

import com.alura.dyno.engine3d.eventsystem.IEvent;
import com.alura.dyno.engine3d.eventsystem.ITreeEventHandler;
import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.handlers.OnCreateEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTestEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {

    @Test public void addEventHandlerTest_1() {
        Script a = new Script("Teste");

        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
                assert true;
            }
        });
        assertEquals(1, a.getEventHandlers().size());
        assertTrue(a.getEventHandlers().containsKey(TreeEventType.OnTest));
    }
    @Test public void addEventHandlerTest_2() {
        final Script a = new Script("Teste");

        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
            }
        });
        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws RuntimeException {
                a.addEventHandler(new OnTestEventHandler() {
                    @Override
                    public void onExecute(OnTestEvent event) {

                    }
                });
            }
        });
    }
    @Test public void addEventHandlerTest_3() {
        final Script a = new Script("Teste");

        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
            }
        });
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {

            }
        });

        assertEquals(2, a.getEventHandlers().size());
        assertTrue(a.getEventHandlers().containsKey(TreeEventType.OnTest));
        assertTrue(a.getEventHandlers().containsKey(TreeEventType.OnCreate));
    }
    @Test public void removeEventHandlerTest_1() {
        final Script a = new Script("Teste");
        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
            }
        });
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {

            }
        });

        a.removeEventHandler(TreeEventType.OnTest);
        assertEquals(1, a.getEventHandlers().size());
        assertTrue(a.getEventHandlers().containsKey(TreeEventType.OnCreate));
    }
    @Test public void removeEventHandlerTest_2() {
        final Script a = new Script("Teste");
        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
            }
        });
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {

            }
        });

        a.removeEventHandler(TreeEventType.OnDrag);
        assertEquals(2, a.getEventHandlers().size());
    }

    @Test public void getEventHandlerTest_1() {
        final Script a = new Script("Teste");
        OnTestEventHandler teh = new OnTestEventHandler() {
            @Override
            public void onExecute(OnTestEvent event) {
            }
        };

        a.addEventHandler(teh);
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {

            }
        });

        assertEquals(teh, a.getEventHandler(TreeEventType.OnTest));
    }
    @Test public void getEventHandlerTest_2() {
        final Script a = new Script("Teste");
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {

            }
        });
    }

    @Test public void onExecuteTest_1() {
        final Script a = new Script("Teste");

        a.addEventHandler(new OnTestEventHandler() {

            @Override
            public void onExecute(OnTestEvent event) {
                assert true;
            }
        });
        a.addEventHandler(new OnCreateEventHandler() {

            @Override
            public void onExecute(OnCreateEvent event) {
                assert false;
            }
        });

        a.getEventHandler(TreeEventType.OnTest)
                .onExecute(new OnTestEventHandler.OnTestEvent());
    }

}