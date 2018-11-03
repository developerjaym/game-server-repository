package com.jaymansmann.gameserver.gameserver.utility;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
public class JCacheAspect {
	private static final Logger LOGGER = Logger.getLogger(JCacheAspect.class.getName());
	@Autowired
	private JCacheManager cacheManager;
	
	@Around("@annotation(JCacheable) && args(key)")
	public Object cache(ProceedingJoinPoint joinPoint, Object key) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	    Method method = methodSignature.getMethod();
	    JCacheable myAnnotation = method.getAnnotation(JCacheable.class);
	    return cacheManager.getOrPutCache(methodSignature.toLongString(), Duration.ofMillis(myAnnotation.time())).getOrPut(key, () -> {
			try {
				return joinPoint.proceed();
			} catch (Throwable e) {
				LOGGER.severe("Error putting new cache in");
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		});
	}
}
