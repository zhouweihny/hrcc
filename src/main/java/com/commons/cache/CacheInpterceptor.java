package com.commons.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.commons.cache.MyCache.Type;
import com.commons.sql.SqlContext;
import com.commons.util.AopTargetUtils;
import com.google.gson.Gson;

public class CacheInpterceptor implements MethodInterceptor {

	@Value("${cache.tables}")
	private String cachetablesStrs;

	@Value("${cache.flag}")
	private Boolean cacheflag;

	public static String[] cachetables;

	public static CacheKeysPool cache_tables = new CacheKeysPool();

	// public static HashMap<String, Set<String>> cache_tables = new
	// HashMap<String, Set<String>>();

	@Autowired
	private CacheManager ehCacheManager;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		// TODO Auto-generated method stub

		if (cacheflag) {
			ReflectiveMethodInvocation invo = (ReflectiveMethodInvocation) invocation;
			MyCache myCache = null;
			loop: for (Method method : AopTargetUtils.getTarget(invo.getProxy()).getClass().getMethods()) {
				if (method.getName().equals(invo.getMethod().getName()) && method.getAnnotation(MyCache.class) != null) {
					Class<?>[] clazzes = method.getParameterTypes();
					if (invocation.getArguments().length == clazzes.length) {
						for (int i = 0; i < clazzes.length; i++) {
							Class<?> clazz = clazzes[i];
							if (null == invocation.getArguments()[i] || !clazz.getName().equals(invocation.getArguments()[i].getClass().getName())) {
								continue loop;
							}
						}
						myCache = method.getAnnotation(MyCache.class);
						break;
					}
				}
			}
			if (myCache != null) {
				if (myCache.type() == Type.QUERY) {
					Cache cache = null;
					String cacheName = createCacheName(invocation, myCache);
					String key = createCacheKey(invocation);
					if (!StringUtils.isEmpty(cacheName) && !StringUtils.isEmpty(key)) {
						if (!ehCacheManager.cacheExists(cacheName)) {
							ehCacheManager.addCache(cacheName);
						}
						cache = ehCacheManager.getCache(cacheName);
						Element e = cache.get(key);
						if (e != null) {
							return e.getObjectValue();
						} else {
							Object object = invocation.proceed();
							cache.put(new Element(key, object));
							return object;
						}
					}
				} else if (myCache.type() == Type.UPDATE) {
					Object object = invocation.proceed();
					Set<String> cacheNames = cache_tables.get(myCache.value());
					for (String cacheName : cacheNames) {
						if (ehCacheManager.cacheExists(cacheName)) {
							ehCacheManager.getCache(cacheName).removeAll();
						}
					}
					return object;
				}

			}
		}
		return invocation.proceed();
	}

	/**
	 * 生成cache名称
	 * 
	 * @param invocation
	 * @param myCache
	 * @return
	 */
	public String createCacheName(MethodInvocation invocation, MyCache myCache) {
		StringBuilder cacheName = new StringBuilder();
		List<String> tables = new ArrayList<String>();
		for (Object o : invocation.getArguments()) {
			if (o instanceof SqlContext) {
				if (cachetables == null) {
					cachetables = cachetablesStrs.toLowerCase().split(",");
				}
				for (String table : cachetables) {
					if (((SqlContext) o).getSql().toString().toLowerCase().indexOf(table.toLowerCase()) >= 0) {
						cacheName.append(table);
						tables.add(table);
					}
				}
				for (String table : tables) {
					cache_tables.putElement(table, cacheName.toString());
				}
			} else {
				String[] values = myCache.value().toLowerCase().split(",");
				for (String table : values) {
					cacheName.append(table);
					tables.add(table);

				}
				for (String table : values) {
					cache_tables.putElement(table, cacheName.toString());
				}
			}
		}
		return cacheName.toString();
	}

	/**
	 * 生成cacheKey
	 * 
	 * @param invocation
	 * @return
	 */
	public String createCacheKey(MethodInvocation invocation) {
		Gson gson = new Gson();
		StringBuilder key = new StringBuilder().append(invocation.getMethod().getName());
		for (Object o : invocation.getArguments()) {
			if (!(o instanceof Class))
				key.append(gson.toJson(o));
		}
		return key.toString();
	}

}
