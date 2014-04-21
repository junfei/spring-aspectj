package br.com.alexandre.spring_aspectj.cache.impl;

import net.spy.memcached.MemcachedClient;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {

	private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);
	
	private final MemcachedClient client;
	
	@Autowired
	public CacheAspect(final MemcachedClient client) {
		this.client = client;
	}
	
	@Around(value = "@within(br.com.alexandre.spring_aspectj.cache.api.Cache) || @annotation(br.com.alexandre.spring_aspectj.cache.api.Cache)")
	public Object aroundServiceMethodAdvice(final ProceedingJoinPoint pjp) throws Throwable {
		String key = null;
		try {
			final Object[] args = pjp.getArgs();
			key = args[0].toString();		
			final Object o = client.get(key);
			logger.info("Buscando key " + key + " do cache.");
			if (o != null) {
				logger.info("Obtido objeto de key " + key + " do cache.");
				return o;
			} else {
				logger.info("Objeto de key " + key + " nao esta em cache. Executando metodo real.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		final Object proceed = pjp.proceed();
		
		if (key != null && proceed != null) {
			try {
				logger.info("Adicionando objeto de key " + key + " no cache.");
				client.set(key, 60, proceed);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		return client.get(key);
	}
}
