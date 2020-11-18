precision highp float;

uniform vec4 u_BackgroundColor;
uniform vec4 u_LineColor;
uniform float u_GridSpacing;
uniform sampler2D u_GridTexture;
uniform mat4 u_InverseVPMatrix;

varying vec4 v_Position;
varying vec4 v_Color;

void main() {
    vec4 viewCoord = u_InverseVPMatrix * v_Position;
    float sampleX = viewCoord.x / u_GridSpacing;
    float sampleY = viewCoord.y / u_GridSpacing;
    float hasLine = texture2D(u_GridTexture, vec2(sampleX, sampleY)).x;

    gl_FragColor =          hasLine  * u_LineColor +
                    (1.0f - hasLine) * u_BackgroundColor;
}