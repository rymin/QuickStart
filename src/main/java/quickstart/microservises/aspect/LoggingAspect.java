package quickstart.microservises.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(quickstart.microservises.LoggableMethod)")
    public void loggableMethod() {
    };


    @Before("loggableMethod()")
    public void loggingInputParams(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());
        logger.info( "Calling method: " + className + "." + methodName + " with params: " + args);
    }

    @AfterReturning(pointcut = "loggableMethod()", returning = "result")
    public void loggingResult(JoinPoint jp, Object result) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        logger.info( "Result for calling method: " + className + "." + methodName + " : " + result.toString());
    }

    @AfterThrowing(pointcut = "loggableMethod()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        logger.info(exception.getLocalizedMessage());
    }

}
