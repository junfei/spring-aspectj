package br.com.alexandre.spring_aspectj.cache.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {

	private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);
	
	@Around(value = "@within(br.com.alexandre.spring_aspectj.cache.api.Cache) || @annotation(br.com.alexandre.spring_aspectj.cache.api.Cache)")
	public Object aroundServiceMethodAdvice(final ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Executando antes do metodo com @Cache");
		
		final Object proceed = pjp.proceed();
		
		logger.info("Executando depois do metodo com @Cache");
		
		return proceed;
	}
}
