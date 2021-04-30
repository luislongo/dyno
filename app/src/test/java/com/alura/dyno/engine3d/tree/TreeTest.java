package com.alura.dyno.engine3d.tree;

import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.script.Script;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test public void addChildTest_1() {
        Glyph parent = new Glyph("parent");
        Glyph childA = new Glyph("childA");
        Glyph childAA = new Glyph("childAA");
        Glyph childAB = new Glyph("childAB");
        Glyph childB = new Glyph("childB");
        Glyph childBA = new Glyph("childBA");
        Glyph childBB = new Glyph("childBB");
        Glyph childBC = new Glyph("childBC");

        parent.addChild(childA);
        childA.addChild(childAA);
        childA.addChild(childAB);
        parent.addChild(childB);
        childB.addChild(childBA);
        childB.addChild(childBB);
        childB.addChild(childBC);

        assertEquals(null, parent.parent);
        assertEquals(parent, childA.parent);
        assertEquals(parent, childB.parent);

        assertEquals(childA, childAA.parent);
        assertEquals(childA, childAB.parent);
        assertEquals(childB, childBA.parent);
        assertEquals(childB, childBB.parent);
        assertEquals(childB, childBC.parent);

        assertEquals(parent, childAA.parent.parent);
        assertEquals(parent, childAB.parent.parent);
        assertEquals(parent, childBA.parent.parent);
        assertEquals(parent, childBB.parent.parent);
        assertEquals(parent, childBC.parent.parent);
    }
    @Test public void removeChildTest_1() {
        Glyph parent = new Glyph("parent");
        Glyph childA = new Glyph("childA");
        Glyph childB = new Glyph("childB");

        parent.addChild(childA);
        parent.addChild(childB);
        assertEquals(2, parent.getChildren().size());

        parent.removeChild(childA);
        assertEquals(1, parent.getChildren().size());
        assertEquals(null, childA.parent);

        parent.removeChild(childB);
        assertEquals(0, parent.getChildren().size());
        assertEquals(null, childB.parent);
    }

    @Test public void addLeafTest_1() {
        Glyph parent = new Glyph("parent");
        Script scriptA = new Script("scriptA");
        Script scriptB = new Script("scriptB");

        parent.addLeaf(scriptA);
        parent.addLeaf(scriptB);

        assertEquals(3, parent.getLeaves().size());
    }
    @Test public void addLeafTest_2() {
        Glyph parentA = new Glyph("parent");
        Glyph parentB = new Glyph("parentB");
        Script script = new Script("script");

        parentA.addLeaf(script);
        assertEquals(2, parentA.getLeaves().size());
        assertEquals(1, parentB.getLeaves().size());
        assertEquals(parentA, script.parent);

        parentB.addLeaf(script);
        assertEquals(1, parentA.getLeaves().size());
        assertEquals(2, parentB.getLeaves().size());
        assertEquals(parentB, script.parent);
    }
    @Test public void removeLeafTest_1() {
        Glyph parent = new Glyph("parent");
        Script scriptA = new Script("scriptA");
        Script scriptB = new Script("scriptB");

        parent.addLeaf(scriptA);
        parent.addLeaf(scriptB);

        parent.removeLeaf(scriptA);

        assertEquals(2, parent.getLeaves().size());
        assertEquals(scriptB, parent.getLeaves().get(1));
    }
    @Test public void removeLeavesTest_1() {
        Glyph parent = new Glyph("parent");
        Script scriptA = new Script("scriptA");
        Script scriptA2 = new Script("scriptA");
        Script scriptB = new Script("scriptB");

        parent.addLeaf(scriptA);
        parent.addLeaf(scriptA2);
        parent.addLeaf(scriptB);

        parent.removeLeaves("scriptA");

        assertEquals(2, parent.getLeaves().size());
        assertEquals(scriptB, parent.getLeaves().get(1));
    }

    @Test public void getLeavesTest_1() {
        Glyph parent = new Glyph("parent");
        Script scriptA = new Script("scriptA");
        Script scriptB = new Script("scriptB");

        parent.addLeaf(scriptA);
        parent.addLeaf(scriptB);

        LinkedList<Script> leaflings = parent.getLeaves("scriptA");

        assertEquals(1, leaflings.size());
        assertEquals(scriptA, leaflings.getFirst());
    }
    @Test public void getLeavesTest_2() {
        Glyph parent = new Glyph("parent");
        Script scriptA = new Script("scriptA");
        Script scriptA2 = new Script("scriptA");
        Script scriptB = new Script("scriptB");

        parent.addLeaf(scriptA);
        parent.addLeaf(scriptA2);
        parent.addLeaf(scriptB);

        LinkedList<Script> leaflings = parent.getLeaves("scriptA");

        assertEquals(2, leaflings.size());
        assertEquals(scriptA, leaflings.getFirst());
        assertEquals(scriptA2, leaflings.getLast());
    }

}