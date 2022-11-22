package com.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j	
@Aspect //공통적으로 적용될 기능이 정의된 AOP클라스임을 의미.
@Component //빈으로 등록
public class LoggerAspect {
	//어라운드는 어드바이스(5개)의 종류중 하나.타겟메서드의 실행 이전/후 모두에게 처리할 필요가 있는 부가기능
	//advice는 부가기능을 구현한 객체
	//execution으로 시작하는 구문은 포인트컷을 지정하는 문법
	//포인트컷: 여러 조인 포인트를 하나로 묶은 개념
	//조인포인트:advice를 적용할 위치
	 @Around("execution(* com.study.domain..*Controller.*(..)) || execution(* com.study.domain..*Service.*(..)) || execution(* com.study.domain..*Mapper.*(..))")
	    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

	        String name = joinPoint.getSignature().getDeclaringTypeName();
	        String type = "";

	        if (name.contains("Controller")) {
	            type = "Controller ===> ";

	        } else if (name.contains("Service")) {
	            type = "ServiceImpl ===> ";

	        } else if (name.contains("Mapper")) {
	            type = "Mapper ===> ";
	        }

	        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
	        return joinPoint.proceed();
	    }
}
