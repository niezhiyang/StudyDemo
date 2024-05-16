package com.nzy.plugin.time;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Objects;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
public class SoMethodVisitor extends AdviceAdapter {
    private String mMethodOwner = "java/lang/System";
    private String mMethodName = "loadLibrary";
    private String mMethodDesc = "(Ljava/lang/String;)V";

    private final int newOpcode = INVOKESTATIC;
    private final String newOwner = "com/nzy/sodemo/SoLoader";
    private final String newMethodName = "loadLibrary";

    private String clazzName;


    protected SoMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String clazzName) {
        super(api, methodVisitor, access, name, descriptor);
        // 拿到当类名，如果是咱们的SoLoader类，就不去替换
        this.clazzName = clazzName;

    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
        LoggerUtil.e("clazzName=" + clazzName + "---mMethodName=" + name + "---mMethodOwner=" + owner + "---" + (Objects.equals(mMethodOwner, owner)) + "---" + (Objects.equals(name, mMethodName)) + "----descriptor" + descriptor);
        if (Objects.equals(mMethodOwner, owner) && Objects.equals(name, mMethodName) && !Objects.equals(clazzName, newOwner)) {
            if (Objects.equals(descriptor, mMethodDesc)) {
                super.visitMethodInsn(opcodeAndSource, newOwner, newMethodName, descriptor, false);
                return;
            }
        }
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);

    }
}
