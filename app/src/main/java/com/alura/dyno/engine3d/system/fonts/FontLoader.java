package com.alura.dyno.engine3d.system.fonts;

import android.content.Context;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.system.fonts.Font.FontBuilder;
import com.alura.dyno.engine3d.system.fonts.FontCharacter.FontCharacterBuilder;

import java.io.InputStream;

public class FontLoader implements FontLoaderConstants {
    FontBuilder fontBuilder;
    Context context;

    int imageSize;
    int lineHeight;
    int base;
    int scaleW;
    int scaleH;

    public FontLoader(Context context) {
        this.context = context;
    }

    public Font load(int atlasResourceId, int fontResourceId) {
        fontBuilder = new FontBuilder();

        loadFontAtlas(atlasResourceId);
        loadSymbolMap(fontResourceId);

        return new Font(fontBuilder);
    }

    private void loadSymbolMap(int fontResourceId) {
        String[] lines = parseLinesFromFile(fontResourceId);

        parsePropertiesFromLine(lines[0]);
        parseCharacters(lines);
    }

    private String[] parseLinesFromFile(int fontResourceId) {
        String symbolString = loadFromSource(fontResourceId);
        return symbolString.split("\r\n");
    }

    private void parsePropertiesFromLine(String line) {
        lineHeight = Integer.parseInt(line.substring(POSINLINE_LINEHEIGHT, POSINLINE_LINEHEIGHT + LENINLINE_OTHERS)
                .replace(" ", ""));
        base = Integer.parseInt(line.substring(POSINLINE_BASE, POSINLINE_BASE + LENINLINE_OTHERS)
                .replace(" ", ""));
        scaleW = Integer.parseInt(line.substring(POSINLINE_SCALEW, POSINLINE_SCALEW + LENINLINE_OTHERS)
                .replace(" ", ""));
        scaleH = Integer.parseInt(line.substring(POSINLINE_SCALEH)
                .replace(" ", ""));

        fontBuilder.setLineHeight(lineHeight).setBase(base).setScale(scaleH, scaleW);
    }

    private void parseCharacters(String[] lines) {
        for (int i = 1; i < lines.length; i++) {
            FontCharacter character = parseFontCharacterFromLine(lines[i]);
            fontBuilder.addCharacter(character);
        }
    }

    private void loadFontAtlas(int atlasResourceId) {
        Texture fontAtlas = new Texture(atlasResourceId, context);
        fontBuilder.setFontAtlas(fontAtlas);
        imageSize = fontAtlas.getImageSize();
    }

    private FontCharacter parseFontCharacterFromLine(String line) {
        FontCharacterBuilder builder = new FontCharacterBuilder();

        builder.setId(Integer.parseInt(line.substring(POSINLINE_ID, POSINLINE_ID + LENINLINE_ID)
                .replace(" ", "")));
        builder.setX(Integer.parseInt(line.substring(POSINLINE_X, POSINLINE_X + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setY(Integer.parseInt(line.substring(POSINLINE_Y, POSINLINE_Y + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setWidth(Integer.parseInt(line.substring(POSINLINE_WIDTH, POSINLINE_WIDTH + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setHeight(Integer.parseInt(line.substring(POSINLINE_HEIGHT, POSINLINE_HEIGHT + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setXoffset(Integer.parseInt(line.substring(POSINLINE_XOFFSET, POSINLINE_XOFFSET + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setYoffset(Integer.parseInt(line.substring(POSINLINE_YOFFSET, POSINLINE_YOFFSET + LENINLINE_OTHERS)
                .replace(" ", "")));
        builder.setXadvance(Integer.parseInt(line.substring(POSINLINE_XADVANCE)
                .replace(" ", "")));
        return builder.build(imageSize, lineHeight, base);
    }

    private String loadFromSource(int fontFileId) {
        InputStream stream = context.getResources().openRawResource(fontFileId);
        StringBuilder build = new StringBuilder();

        byte[] buf = new byte[1024];
        int length;

        try {
            while ((length = stream.read(buf)) != -1) {
                build.append(new String(buf, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading resource: " + fontFileId);
        }

        return build.toString();
    }
}
