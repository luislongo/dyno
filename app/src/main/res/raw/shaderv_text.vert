attribute vec3 a_Position;
attribute vec4 a_Color;
attribute vec2 a_TextureCoords;

uniform mat4 u_ModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;
uniform float u_FontSize;

varying vec2 v_TextureCoords;

void main()
{
    gl_Position = u_ProjectionMatrix *
                  u_ViewMatrix *
                  u_ModelMatrix *
                  vec4(u_FontSize * a_Position, 1.0f);
    v_TextureCoords = a_TextureCoords;
}

