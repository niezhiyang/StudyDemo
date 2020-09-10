package cn.nzy.butterknife_compiler;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import cn.nzy.butterknife_annotations.Route;

/**
 * @author niezhiyang
 * since 2020/9/10
 */
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    private static final String TAG = "RouterProcessor";
    private HashMap<String, String> clazzMap = new HashMap<>();
    private Filer mFileUtils;
    private Messager mMessager;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 因为这里可能会执行多次，所以这里需要clear
        clazzMap.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        for (Element element : elements) {
            // 直接拿到的就是类，所以强转成类的type
            TypeElement typeElement = (TypeElement) element;
            // 得到全限定名
            String clazzName = typeElement.getQualifiedName().toString();
            // 拿到注解
            Route route = typeElement.getAnnotation(Route.class);
            // 注解的value
            String key = route.value();

            // 添加到map中
            clazzMap.put(key, clazzName + ".class");

        }

        if (clazzMap.size() > 0) {
            mMessager.printMessage(Diagnostic.Kind.NOTE,TAG+clazzMap.size());
            Writer writer = null;
            // 创建文件的名字
            String clazzName = "RouterUtil" + System.currentTimeMillis();
            try {
                JavaFileObject source = mFileUtils.createSourceFile("com.nzy.arouter.util." + clazzName);
                writer = source.openWriter();
                StringBuilder builder = new StringBuilder();
                builder.append("package com.nzy.arouter.util;\n");
                builder.append("import cn.nzy.butterknife_api.IRouter;\n");
                builder.append("import cn.nzy.butterknife_api.ARouter;\n");
                builder.append("public class " + clazzName + " implements IRouter {\n" +
                        "    @Override\n" +
                        "    public void putActivity() {\n");
                Iterator<String> iterator = clazzMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = clazzMap.get(key);
                    builder.append("ARouter.getInstance().addActivity(\"" + key + "\"," + value + ");");

                }
                builder.append("    }\n" +
                        "}\n");
                writer.write(builder.toString());
                writer.flush();
                writer.close();

            } catch (IOException e) {
//                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        // 用来写文件的
        mFileUtils = processingEnvironment.getFiler();
        // 可以打印日志
        mMessager = processingEnvironment.getMessager();
        super.init(processingEnvironment);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<String>();
        // 需要添加的注解类型
        annotationTypes.add(Route.class.getCanonicalName());
        return annotationTypes;

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
