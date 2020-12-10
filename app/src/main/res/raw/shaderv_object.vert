precision mediump float;

attribute vec3 a_Position;
attribute vec4 a_Color;

uniform mat4 u_ModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;

varying vec4 v_Color;

void main() {
    gl_Position = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix * vec4(a_Position, 1.0f);
    v_Color = vec4(a_Position, 1.0f);
}