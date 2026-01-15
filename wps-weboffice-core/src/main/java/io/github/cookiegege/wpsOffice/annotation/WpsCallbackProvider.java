package io.github.cookiegege.wpsOffice.annotation;

import java.lang.annotation.*;

/**
 * @author JoSuper
 * @date 2026/1/8 11:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WpsCallbackProvider {

    String bizCode(); // 业务标识

}
