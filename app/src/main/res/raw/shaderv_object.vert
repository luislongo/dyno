precision mediump float;

attribute vec3 a_Position;
attribute vec4 a_Color;
attribute vec3 a_Normal;

uniform mat4 u_ModelMatrix;
uniform mat4 u_ViewMatrix;
uniform mat4 u_ProjectionMatrix;

varying vec4 v_Color;
varying vec3 v_Normal;

void main() {
    gl_Position = u_ProjectionMatrix * u_ViewMatrix * u_ModelMatrix * vec4(a_Position, 1);
    v_Color = a_Color;
    v_Normal = vec3(u_ViewMatrix * u_ModelMatrix * vec4(a_Normal, 0.0));
}