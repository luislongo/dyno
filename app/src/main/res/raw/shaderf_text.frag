precision mediump float;

uniform sampler2D u_FontAtlas;
uniform vec4 u_FontColor;

varying vec2 v_TexCoords;

void main()
{
//texture2D(u_FontAtlas, v_TexCoords).a * 
   gl_FragColor = u_FontColor;
}