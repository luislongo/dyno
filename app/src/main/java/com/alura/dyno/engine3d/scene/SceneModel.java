package com.alura.dyno.engine3d.scene;

import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.engine3d.script.Script;
import com.alura.dyno.engine3d.tree.Tree;

import java.util.HashMap;

public class SceneModel {
    private Tree<Glyph, Script> tree;
    Camera mainCamera;

    private HashMap<String, Shader> shaders;
    private HashMap<String, Texture> textures;
    private HashMap<String, Material> materials;

    public SceneModel() {
        Glyph root = new Glyph("ROOT");
        tree = new Tree(root);

        textures = new HashMap<>();
        materials = new HashMap<>();
        shaders = new HashMap<>();
    }
    public Glyph getRoot() {
        return tree.getRoot();
    }
    public Camera getMainCamera() {return mainCamera;}
    public void setMainCamera(Camera mainCamera) {this.mainCamera = mainCamera;}
    public void setTreeChangedListener(ITreeChangedListener listener) {
        tree.setTreeChangedListener(listener);
    }

    public void cacheShader(String name, Shader shader) {
        if(shaders.containsKey(name)) {
            shaders.remove(name);
        }

        this.shaders.put(name, shader);
    }
    public void cacheTexture(String name, Texture texture) {
        if(textures.containsKey(name)) {
            textures.remove(name);
        }

        this.textures.put(name, texture);
    }
    public void cacheMaterial(String name, Material material) {
        if(materials.containsKey(name)) {
            materials.remove(name);
        }

        this.materials.put(name, material);
    }
    public Shader getShader(String name)
    {
        return shaders.get(name);
    }
    public Texture getTexture(String name) {
        return textures.get(name);
    }
    public Material getMaterial(String name) { return materials.get(name); }
}
