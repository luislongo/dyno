package com.alura.dyno.engine3d.eventsystem;

import com.alura.dyno.engine3d.eventsystem.handlers.OnCreateEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTest2EventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTestEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.script.Script;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeEventDispatcherTest {
    private final Glyph ROOT = root();
    private final TreeEventDispatcher dispatcher = new TreeEventDispatcher(ROOT);

    @Test public void sendDownTheTreeTest_1() {
        OnTestEventHandler.OnTestEvent event = new OnTestEventHandler.OnTestEvent();

        dispatcher.sendDownTheTree(event);
        assertEquals(6, event.count);
    }
    @Test public void sendDownTheChildrenOfTest_1() {
        OnTestEventHandler.OnTestEvent event = new OnTestEventHandler.OnTestEvent();

        dispatcher.sendDownTheChildrenOf(root(), event);
        assertEquals(5, event.count);
    }


    private Glyph root() {
        Glyph parent = new Glyph("parent");
        parent.addLeaf(new ScriptTest1A("test_1AParent"));
        Glyph childA = new Glyph("childA");
        childA.addLeaf(new ScriptTest1A("test_1AChildA"));
        Glyph childAA = new Glyph("childAA");
        childAA.addLeaf(new ScriptTest1A("test1AChildAA"));
        childAA.addLeaf(new ScriptTest1B("test1BChildAA"));
        childAA.addLeaf(new ScriptTest2("test2ChildAA"));
        Glyph childAB = new Glyph("childAB");
        childAB.addLeaf(new ScriptTest1A("test_1AChildAB"));
        Glyph childB = new Glyph("childB");
        childB.addLeaf(new ScriptTest1A("test_1AChildB"));
        childB.addLeaf(new ScriptTest2("test_2ChildB"));

        parent.addChild(childA);
        childA.addChild(childAA);
        childA.addChild(childAB);
        parent.addChild(childB);

        return parent;
    }
    private static class ScriptTest1A extends Script {

        public ScriptTest1A(String name) {
            super(name);
            this.addEventHandler(new OnTestEventHandler() {
                @Override
                public void onExecute(OnTestEvent event) {
                    event.count++;
                }
            });
        }
    }
    private static class ScriptTest1B extends Script {

        public ScriptTest1B(String name) {
            super(name);
            this.addEventHandler(new OnTestEventHandler() {
                @Override
                public void onExecute(OnTestEvent event) {
                    event.count++;
                }
            });
        }
    }
    private static class ScriptTest2 extends Script {

        public ScriptTest2(String name) {
            super(name);
            this.addEventHandler(new OnTest2EventHandler() {
                @Override
                public void onExecute(OnTest2Event event) {
                    event.count++;
                }
            });
        }
    }

}