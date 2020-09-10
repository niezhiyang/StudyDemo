package cn.nzy.butterknife_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niezhiyang
 * since 2020/9/10
 */
// 注解用在哪里， ElementType.TYPE代表类上
@Target({ElementType.TYPE})
//   注解的生命周期，
//    SOURCE, 只有源码的时候
//    CLASS,  编译型注解，也就是编译成class之后还是会有
//    RUNTIME; 运行时也存在
@Retention(RetentionPolicy.CLASS)
public @interface Route {

    // 如果只有一个参数，最好写成 value ，约定俗称的，并不是强制的
    String value();

}
