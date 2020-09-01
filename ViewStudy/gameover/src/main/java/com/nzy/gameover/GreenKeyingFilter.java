package com.nzy.gameover;

public class GreenKeyingFilter extends BasicFilter {

    @Override
    protected String getFragmentShader() {

        return
                "precision mediump float;\n"
                        + "uniform sampler2D " + UNIFORM_TEXTURE0 + ";\n"
                        + "varying vec2 " + VARYING_TEXCOORD + ";\n"

                        + "void main(){\n"
                        + "   vec2 pos = textureCoordinate.xy; \n"
                        + "   vec4 textureColor = texture2D(" + UNIFORM_TEXTURE0 + "," + VARYING_TEXCOORD + ");\n"
                        + "   vec4 color = vec4(0.0,1.0,0.0,1.0);\n"
                        + "   float maskY = 0.2989 * color.r + 0.5866 * color.g + 0.1145 * color.b;\n" +
                        "     float maskCr = 0.7132 * (color.r - maskY);\n" +
                        "     float maskCb = 0.5647 * (color.b - maskY);\n" +
                        "     float Y = 0.2989 * textureColor.r + 0.5866 * textureColor.g + 0.1145 * textureColor.b;\n" +
                        "     float Cr = 0.7132 * (textureColor.r - Y);\n" +
                        "     float Cb = 0.5647 * (textureColor.b - Y);\n" +
                        "     float thresholdSensitivity = 0.4;\n" +
                        "     float smoothing = 0.1;\n" +
                        "     float blendvalue = 1.0 - smoothstep(thresholdSensitivity, thresholdSensitivity+smoothing, distance(vec2(Cr,Cb), vec2(maskCr, maskCb)));\n" +
                        "     \n" +

                        "   gl_FragColor = mix(textureColor, vec4(0.0,0.0,0.0,0.0),blendvalue);\n"
                        + "   \n"
                        + "}\n";
    }
}
