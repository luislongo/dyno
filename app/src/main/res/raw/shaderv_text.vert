uniform mat4 u_MVPMatrix;
uniform float u_FontSize;

attribute vec4 a_Position;
attribute vec4 a_Color;
attribute vec2 a_TexCoord;

varying vec2 v_TexCoords;

void main()
{
    gl_Position =  u_MVPMatrix * vec4(a_Position.x * u_FontSize, a_Position.y * u_FontSize, a_Position.z * u_FontSize, 1.0f);
    v_TexCoords = a_TexCoord;
}

