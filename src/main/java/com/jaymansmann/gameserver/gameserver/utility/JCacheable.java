package com.jaymansmann.gameserver.gameserver.utility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JCacheable {

	/**
	 * Number of milliseconds
	 * @return
	 */
	int time();


}
