package cn.nzy.butterknife_compiler;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class ProxyInfoClass
{
    private String packageName;
    private String proxyClassName;
    private TypeElement typeElement;

    public Map<Integer, VariableElement> injectVariables = new HashMap<>();

    // 这个是 api 库中的方法名
    public static final String PROXY = "ViewInject";

    public ProxyInfoClass(Elements elementUtils, TypeElement classElement)
    {
        this.typeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        //classname
        String className = ClassValidator.getClassName(classElement, packageName);
        this.packageName = packageName;
        this.proxyClassName = className + "_" + PROXY;
    }


    public String generateJavaCode()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code. Do not modify!\n");
        //
        builder.append("package ").append(packageName).append(";\n\n");
        //包名改一下
        builder.append("import cn.nzy.butterknife_api.*;\n");
        builder.append('\n');

        builder.append("public class ").append(proxyClassName).append(" implements " + ProxyInfoClass.PROXY + "<" + typeElement.getQualifiedName() + ">");
        builder.append(" {\n");

        generateMethods(builder);
        builder.append('\n');

        builder.append("}\n");
        return builder.toString();

    }


    private void generateMethods(StringBuilder builder)
    {

        builder.append("@Override\n ");
        // 方法名 更改一下
        builder.append("public void inject(" + typeElement.getQualifiedName() + " host, Object source ) {\n");


        for (int id : injectVariables.keySet())
        {
            VariableElement element = injectVariables.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append(" if(source instanceof android.app.Activity){\n");
            builder.append("host." + name).append(" = ");
            builder.append("(" + type + ")(((android.app.Activity)source).findViewById( " + id + "));\n");
            builder.append("\n}else{\n");
            builder.append("host." + name).append(" = ");
            builder.append("(" + type + ")(((android.view.View)source).findViewById( " + id + "));\n");
            builder.append("\n};");





        }
        builder.append("  }\n");


    }

    public String getProxyClassFullName()
    {
        return packageName + "." + proxyClassName;
    }

    public TypeElement getTypeElement()
    {
        return typeElement;
    }


}