precision mediump float;

uniform sampler2D u_FontAtlas;
uniform vec4 u_FontColor;

varying vec2 v_TextureCoords;

void main()
{
   gl_FragColor = texture2D(u_FontAtlas, v_TextureCoords).a * u_FontColor;
}