
package ir.maktab.finalproject.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Pointcut("within(ir.maktab.finalproject.model..*)" +
            " || within(ir.maktab.finalproject.service..*)" +
            " || within(ir.maktab.finalproject.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }


    @Around("execution(* ir.maktab.finalproject.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        ObjectMapper mapper = new ObjectMapper();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Object[] array = proceedingJoinPoint.getArgs();

        final StopWatch stopWatch = new StopWatch();

        logger.info("method invoke " + className + "." + methodName);
        //"Response : " + mapper.writeValueAsString(array) )


        Object result = proceedingJoinPoint.proceed();

        return result;
    }

}

