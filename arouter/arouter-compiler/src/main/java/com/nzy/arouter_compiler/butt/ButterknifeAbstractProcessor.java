package com.nzy.arouter_compiler.butt;

import com.google.auto.service.AutoService;
import com.nzy.arouter_annotations.BindView;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
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
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;


/**
 * on 2018/5/2.
 * created by niezhiyang
 */
@AutoService(Processor.class)
public class ButterknifeAbstractProcessor extends AbstractProcessor {
    //存储信息
    private Map<String, ProxyInfoClass> mProxyMap = new HashMap<String, ProxyInfoClass>();
    private Filer mFileUtils;
    private Elements mElementUtils;
    /**
     * Element 的子类
     - VariableElement //一般代表成员变量
     - ExecutableElement //一般代表类中的方法
     - TypeElement //一般代表代表类
     - PackageElement //一般代表Package
     */
    private Messager mMessager;


    /**
     *  //初始化父类的方法
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);

        //跟文件相关的辅助类，生成JavaSourceCode.
        mFileUtils = processingEnv.getFiler();
        //跟元素相关的辅助类，帮助我们去获取一些元素相关的信息。
        mElementUtils = processingEnv.getElementUtils();
        //跟日志相关的辅助类
        mMessager = processingEnv.getMessager();
    }

    /**
     * 返回支持的注解类型  一般都是固定写法一般都是固定写法
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes(){
        /** Class
         * getName            my.ExternalClassConfig
         getCanonicalName   my.ExternalClassConfig
         getSimpleName      ExternalClassConfig
         getName            my.ExternalClassConfig$InternalConfig
         getCanonicalName   my.ExternalClassConfig.InternalConfig
         getName()返回的是虚拟机里面的class的表示，而getCanonicalName()返回的是更容易理解的表示。其实对于大部分class来说这两个方法没有什么不同的。但是对于array或内部类来说是有区别的。
         另外，类加载（虚拟机加载）的时候需要类的名字是getName
         */
        Set<String> annotationTypes = new LinkedHashSet<String>();
        annotationTypes.add(BindView.class.getCanonicalName());
        return annotationTypes;
    }

    /**
     * 返回支持的源码版本 一般都写最近的 一般都是固定写法
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }


    /**作用:
     * 1.收集信息
     *  就是根据你的注解声明，拿到对应的Element，然后获取到我们所需要的信息，这个信息肯定是为了后面生成JavaFileObject所准备的
     * 2 .生成代理类（本文把编译时生成的类叫代理类）
     * 我们会针对每一个类生成一个代理类，例如MainActivity我们会生成一个MainActivity$$ViewInjector
     *
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //因为process可能会多次调用,避免生成重复的代理类，避免生成类的类名已存在异常
        mProxyMap.clear();
        // 通过roundEnvironment.getElementsAnnotatedWith拿到我们通过@BindView注解的元素，这里返回值，按照我们的预期应该是VariableElement集合，因为我们用于成员变量上。
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //一、收集信息
        for (Element element : elements){
            //field type  拿到成员变量
            VariableElement variableElement = (VariableElement) element;
            //class type  类 拿到对应的类信息 从而生成生成ProxyInfo对象
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();//TypeElement
            String qualifiedName = typeElement.getQualifiedName().toString();

            ProxyInfoClass proxyInfoClass = mProxyMap.get(qualifiedName);
            if (proxyInfoClass == null){
                proxyInfoClass = new ProxyInfoClass(mElementUtils, typeElement) ;
                mProxyMap.put(qualifiedName, proxyInfoClass);
            }
            BindView annotation = variableElement.getAnnotation(BindView.class);
            int id = annotation.value();
            proxyInfoClass.injectVariables.put(id, variableElement);
        }

        // 生成代理类 用i/o流 写成一个代理类
        for(String key : mProxyMap.keySet()){
            ProxyInfoClass proxyInfoClass = mProxyMap.get(key);
            JavaFileObject sourceFile = null;
            try {
                sourceFile = mFileUtils.createSourceFile(
                        proxyInfoClass.getProxyClassFullName(), proxyInfoClass.getTypeElement());
                Writer writer = sourceFile.openWriter();
                writer.write(proxyInfoClass.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
