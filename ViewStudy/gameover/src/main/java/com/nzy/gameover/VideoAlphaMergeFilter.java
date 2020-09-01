package com.nzy.gameover;

public class VideoAlphaMergeFilter extends BasicFilter {
    @Override
    protected String getFragmentShader() {
        return
                "precision mediump float;\n"
                        + "uniform sampler2D " + UNIFORM_TEXTURE0 + ";\n"
                        + "varying vec2 " + VARYING_TEXCOORD + ";\n"

                        + "void main(){\n" +
                        " vec2 texOne = " +VARYING_TEXCOORD  + ";\n"
                        + "   vec4 color1 = texture2D(" + UNIFORM_TEXTURE0 + ", vec2(texOne.x/2.0,texOne.y));\n"
                        + "   vec4 color2 = texture2D(" + UNIFORM_TEXTURE0 + ",vec2(texOne.x/2.0 + 0.5,texOne.y));\n" +
                        "color2.a = color1.r; color2.rgb = color2.rgb * color1.r; " +
                        "   gl_FragColor = color2;\n"

                        + "}\n";
    }
}
