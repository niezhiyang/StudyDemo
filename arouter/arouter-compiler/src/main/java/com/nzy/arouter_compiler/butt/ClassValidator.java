package com.nzy.arouter_compiler.butt;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

final class ClassValidator
{
    static boolean isPrivate(Element annotatedClass)
    {
        return annotatedClass.getModifiers().contains("private");
    }

    static String getClassName(TypeElement type, String packageName)
    {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen)
                .replace('.', '$');
    }
}