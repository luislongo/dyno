precision mediump float;

attribute vec3 a_Position;
attribute vec3 a_Color;

varying vec4 v_Position;

void main() {
    v_Position = vec4(a_Position, 1.0f);
    gl_Position = vec4(a_Position, 1.0f);
}