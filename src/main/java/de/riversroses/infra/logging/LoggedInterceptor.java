package de.riversroses.infra.logging;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@InterceptorBean(Logged.class)
public class LoggedInterceptor implements MethodInterceptor<Object, Object> {

  @Override
  public Object intercept(MethodInvocationContext<Object, Object> context) {
    Logger log = LoggerFactory.getLogger(context.getDeclaringType());
    String name = context.getMethodName();
    log.debug("[LOG] Enter {}()", name);
    try {
      Object result = context.proceed();
      log.debug("[LOG] Exit {}()", name);
      return result;
    } catch (Throwable t) {
      log.error("[LOG] Exception in {}(): {}", name, t.toString());
      throw t;
    }
  }
}
