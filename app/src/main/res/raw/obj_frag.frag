precision mediump float;

varying vec2 v_UVs;

uniform sampler2D u_Albedo;

void main() {
   gl_FragColor = texture2D(u_Albedo, v_UVs);
}