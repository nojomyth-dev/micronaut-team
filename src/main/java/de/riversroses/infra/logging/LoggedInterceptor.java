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
        String methodName = context.getMethodName();

        log.info("[LOG] Enter {}()", methodName);

        try {
            Object result = context.proceed();
            log.info("[LOG] Exit {}()", methodName);
            return result;
        } catch (Throwable t) {
            log.error("[LOG] Exception in {}(): {}", methodName, t.getMessage(), t);
            throw propagate(t);
        }
    }

    private RuntimeException propagate(Throwable t) {
        if (t instanceof RuntimeException runtimeException) {
            return runtimeException;
        }
        if (t instanceof Error error) {
            throw error;
        }
        return new RuntimeException(t);
    }
}
