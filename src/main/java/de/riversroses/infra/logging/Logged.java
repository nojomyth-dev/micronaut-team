package de.riversroses.infra.logging;

import io.micronaut.aop.Around;
import io.micronaut.core.annotation.Introspected;

import java.lang.annotation.*;

@Around
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Introspected
public @interface Logged {
  String value() default "";
}