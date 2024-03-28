package com.nzy.plugin.time;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author niezhiyang
 * since 2024/3/28
 */
public class SoClassVisitor extends ClassVisitor {

   private String clazzName = "";

   public SoClassVisitor(int api, ClassVisitor classVisitor) {
      super(api, classVisitor);
   }

   @Override
   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      clazzName = name;
      super.visit(version, access, name, signature, superName, interfaces);
   }

   @Override
   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
      MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
      return new SoMethodVisitor(api, methodVisitor, access, name, descriptor,clazzName);
   }
}
