package epam.spring.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;

@Aspect
public class StatisticsAspect {
private Map<Class<?>,Integer> counter;

@AfterReturning(pointcut = "allLogEventsMethods()")
    public void count(JoinPoint joinPoint) {
    Class<?> currentClass = joinPoint.getTarget().getClass();

    if(!counter.containsKey(currentClass)) {
        counter.put(currentClass,0);
    }

    counter.put(currentClass,counter.get(currentClass)+1);
}
}
