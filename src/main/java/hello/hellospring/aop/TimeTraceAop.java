package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop라고 선언
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") // 어디에 적용할건지 타게팅해주는 역할 : 프로젝트 하위는 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+ joinPoint.toString()+ " "+ timeMs + "ms");
        }
    }
}
