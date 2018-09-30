package com.commons.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Du.Jun
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyCache {
	/** cache 表名称 */
	public String value() default "";

	public enum Type {
		QUERY, UPDATE
	};

	public Type type() default Type.QUERY;

}
