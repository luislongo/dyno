precision mediump float;

varying vec2 v_UVs;

uniform sampler2D u_Albedo;
uniform float u_TextSoftEdge;
uniform float u_TextHardEdge;
uniform vec4 u_TextColor;

void main() {
   float factor = 1.0 - texture2D(u_Albedo, v_UVs).r;
   float transparency = 1.0 - smoothstep(u_TextSoftEdge, u_TextHardEdge, factor);
   gl_FragColor = transparency * u_TextColor;
}