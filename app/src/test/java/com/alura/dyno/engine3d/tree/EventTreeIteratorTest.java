package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.eventsystem.TreeEventType;
import com.alura.dyno.engine3d.eventsystem.events.OnCreateEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnDragEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnCreateEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnDragEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnTestEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.script.Script;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTreeIteratorTest {
    private final Glyph ROOT = root();

    @Test public void fromNodeDownTest_1() {
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(ROOT, TreeEventType.OnTest);
        assertEquals(6, iterator.count());
    }
    @Test public void fromNodeDownTest_2() {
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(ROOT, TreeEventType.OnCreate);
        assertEquals(4, iterator.count());
    }
    @Test public void fromNodeDownTest_3() {
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(ROOT, TreeEventType.OnDrag);
        assertEquals(3, iterator.count());
    }
    @Test public void fromNodeDownTest_4() {
        EventTreeIterator iterator = EventTreeIterator.fromNodeDown(ROOT, TreeEventType.OnScale);
        assertEquals(0, iterator.count());
    }
    @Test public void fromNodeDownTest_5() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeDown(ROOT.getChild("childA"), TreeEventType.OnTest);
        assertEquals(3, iterator.count());
    }
    @Test public void fromNodeDownTest_6() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeDown(ROOT.getChild("childA"), TreeEventType.OnDrag);
        assertEquals(1, iterator.count());
    }
    @Test public void fromNodeDownTest_7() {
        EventTreeIterator iteratorA = EventTreeIterator
                .fromNodeDown(ROOT.getChild("childB").getChild("childBA"), TreeEventType.OnTest);
        assertEquals(0, iteratorA.count());

        EventTreeIterator iteratorB = EventTreeIterator
                .fromNodeDown(ROOT.getChild("childB").getChild("childBA"), TreeEventType.OnCreate);
        assertEquals(0, iteratorB.count());

        EventTreeIterator iteratorC = EventTreeIterator
                .fromNodeDown(ROOT.getChild("childB").getChild("childBA"), TreeEventType.OnDrag);
        assertEquals(0, iteratorC.count());
    }

    @Test public void fromNodeUpTest_1() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeUp(ROOT.getChild("childA"), TreeEventType.OnTest);
        assertEquals(2, iterator.count());
    }
    @Test public void fromNodeUpTest_2() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeUp(ROOT.getChild("childA").getChild("childAB"), TreeEventType.OnTest);
        assertEquals(3, iterator.count());
    }
    @Test public void fromNodeUpTest_3() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeUp(ROOT.getChild("childA").getChild("childAB"), TreeEventType.OnDrag);
        assertEquals(1, iterator.count());
    }
    @Test public void fromNodeUpTest_4() {
        EventTreeIterator iterator = EventTreeIterator
                .fromNodeUp(ROOT.getChild("childB").getChild("childBA"), TreeEventType.OnDrag);
        assertEquals(1, iterator.count());
    }

    private Glyph root() {
        Glyph parent = new Glyph("parent");
        parent.addLeaf(new ScriptTest("testParent"));

        Glyph childA = new Glyph("childA");
        childA.addLeaf(new ScriptTest("testChildA"));
        childA.addLeaf(new ScriptCreate("createChildA"));

        Glyph childAA = new Glyph("childAA");
        childAA.addLeaf(new ScriptTest("testChildAA"));
        childAA.addLeaf(new ScriptCreate("createChildAA"));

        Glyph childAB = new Glyph("childAB");
        childAB.addLeaf(new ScriptTest("testChildAB"));
        childAB.addLeaf(new ScriptCreate("createChildAB"));
        childAB.addLeaf(new ScriptDrag("dragChildAB"));

        Glyph childB = new Glyph("childB");
        childB.addLeaf(new ScriptTest("testChildB"));
        childB.addLeaf(new ScriptDrag("dragChildB"));

        Glyph childBA = new Glyph("childBA");
        Glyph childBB = new Glyph("childBB");
        childBB.addLeaf(new ScriptTest("testChildBB"));
        childBB.addLeaf(new ScriptCreate("createChildBB"));
        childBB.addLeaf(new ScriptDrag("dragChildBB"));

        Glyph childBC = new Glyph("childBC");

        parent.addChild(childA);
        childA.addChild(childAA);
        childA.addChild(childAB);
        parent.addChild(childB);
        childB.addChild(childBA);
        childB.addChild(childBB);
        childB.addChild(childBC);

        return parent;
    }

    private static class ScriptTest extends Script {

        public ScriptTest(String name) {
            super(name);
            this.addEventHandler(new OnTestEventHandler() {
                @Override
                public void onExecute(OnTestEvent event) {

                }
            });
        }
    }
    private static class ScriptCreate extends Script {

        public ScriptCreate(String name) {
            super(name);
            this.addEventHandler(new OnCreateEventHandler() {
                @Override
                public void onExecute(OnCreateEvent event) {

                }
            });
        }
    }
    private static class ScriptDrag extends Script {

        public ScriptDrag(String name) {
            super(name);
            this.addEventHandler(new OnDragEventHandler() {
                @Override
                public void onExecute(OnDragEvent event) {

                }
            });
        }
    }

}