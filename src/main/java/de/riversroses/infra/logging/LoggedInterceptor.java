package de.riversroses.infra.logging;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Der Interceptor muss eine Bean sein.
// TODO: Der Interceptor muss für das @interface registriert sein.
public class LoggedInterceptor implements MethodInterceptor<Object, Object> {
  
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
      
        // TODO: Verwendet hier die aufrufende Klasse. Tipp: Schaut mal im context Object.      
        Logger log = LoggerFactory.getLogger(this.getClass());
        // TODO: Verwendet hier den aufrufenden Methodennamen. Tipp: Schaut mal im context Object.
        String methodName = "dummy";

        // TODO: Hier könnt ihr loggen.

        try {
            // Sehr wichtig: Ihr müsst die eigentliche Methode wieder aufrufen!
            Object result = context.proceed();
            return result;
        } catch (Throwable t) {
            throw propagate(t);
        }
    }

    // Helper Method, hier müsst ihr nichts anpassen
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
