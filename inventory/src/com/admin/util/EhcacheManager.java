package com.admin.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheManager {
	
	private static Logger logger = LoggerFactory.getLogger(EhcacheManager.class);

	private static final String path = "/ehcache.xml";
	private URL url;
	private CacheManager manager;
	private static EhcacheManager ehCache;

	private EhcacheManager(String path) {
		url = getClass().getResource(path);
		manager = CacheManager.create(url);
	}

	public static EhcacheManager getInstance() {
		if (ehCache == null) {
			logger.info("EhCache启动！");
			ehCache = new EhcacheManager(path);
		}
		return ehCache;
	}
	
	public Cache get(String cacheName) {
		return manager.getCache(cacheName);
	}

	/**
	 * 添加缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName, String key, Object value) {
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * 查询缓存
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}
	
	public Map<String, Object> queryKeys(String cacheName){
		Map<String, Object> map = new HashMap<String, Object>();
		Cache cache = manager.getCache(cacheName);
		map.put("keySize", cache.getSize());
		map.put("keyList", cache.getKeys());
		return map;
	}

	/**
	 * 删除缓存
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}

}
