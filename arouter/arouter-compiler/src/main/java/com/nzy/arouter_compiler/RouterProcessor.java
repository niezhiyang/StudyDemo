package com.nzy.arouter_compiler;

import com.google.auto.service.AutoService;
import com.nzy.arouter_annotations.Route;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author niezhiyang
 * since 2020/9/10
 */
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    private HashMap<String, String> clazzMap = new HashMap<>();
    private Filer mFileUtils;
    private Messager mMessager;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Writer writer = null;
        JavaFileObject source = null;
        try {
            source = mFileUtils.createSourceFile("com.nzy.arouter.util.niezhiyang.java" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer = source.openWriter();
            writer.write("lalalallalalal");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        clazzMap.clear();
//        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Route.class);
//        mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+"1111111111111111");
//        for (Element element : elements) {
//            TypeElement typeElement = (TypeElement) element.getEnclosingElement();
//            String clazzName = typeElement.getQualifiedName().toString();
//            mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+"2222222222222");
//
//            Route route = typeElement.getAnnotation(Route.class);
//            String key = route.value();
//
//            clazzMap.put(key, clazzName + ".class");
//
//        }
//
//        if (clazzMap.size() > 0) {
//            mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+"3333333333333");
//            Writer writer = null;
//            // 创建文件的名子
//            String clazzName = "RouterUtil" + System.currentTimeMillis();
//            try {
//                mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+"55555");
//                JavaFileObject source = mFileUtils.createSourceFile("com.nzy.arouter.util." + clazzName);
//                writer = source.openWriter();
//                StringBuilder builder = new StringBuilder();
//                builder.append("package com.nzy.arouter.util;\n");
//                builder.append("import com.nzy.arouter_api.IRouter;\n");
//                builder.append("import com.nzy.arouter_api.ARouter;\n");
//                builder.append("public class " + clazzName + " implements IRouter {\n" +
//                        "    @Override\n" +
//                        "    public void putActivity() {\n");
//                Iterator<String> iterator = clazzMap.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    String value = clazzMap.get(key);
//                    builder.append("ARouter.getInstance().addActivity(" + key + "," + value + ");");
//
//                }
//                mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+"66666");
//                builder.append("    }\n" +
//                        "}\n");
//                writer.flush();
//
//            } catch (IOException e) {
//                mMessager.printMessage(Diagnostic.Kind.NOTE,"sssssssssss"+e.toString()+"44444444");
////                e.printStackTrace();
//            } finally {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        mFileUtils = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
        super.init(processingEnvironment);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<String>();
        annotationTypes.add(Route.class.getCanonicalName());
        return annotationTypes;

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
