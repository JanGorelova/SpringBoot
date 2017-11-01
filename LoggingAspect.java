package epam.spring.core;

import com.sun.javafx.util.Logging;
import org.apache.commons.logging.Log;
import org.aspectj.bridge.MessageUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import static org.aspectj.bridge.MessageUtil.info;

@Aspect
public class LoggingAspect {
    @Before("allLogEventsMethods()")
    public  void logBefore(JoinPoint joinPoint) {
        MessageUtil.info( "Before : "+
                joinPoint.getTarget().getClass().getSimpleName() +
                " "+ joinPoint.getSignature().getName()
        );
    }

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventsMethods() {}

    @AfterReturning(pointcut = "allLogEventsMethods()",
    returning = "retVal")
    public void logAfter(Object retVal) {
        MessageUtil.info("Returned value : " + retVal);
    }

    @AfterThrowing(
            pointcut = "allLogEventsMethods()",
            throwing = "ex")
    public void logAfter(Throwable ex) {
        MessageUtil.info("Thrown : " + ex);
    }
}
