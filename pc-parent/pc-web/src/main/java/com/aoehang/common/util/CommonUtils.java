package com.aoehang.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 基本工具类
 * 
 * @author QYANZE
 *
 */
public class CommonUtils {

	/**
	 * 对象转map
	 * 
	 * @param obj
	 */
	public static Map<String, Object> obj2Map(Object obj) {
		Class<?> clz = obj.getClass();
		Field[] field = clz.getDeclaredFields();
		
		HashMap<String, Object> map = new HashMap<>();
		for (int i = 0; i < field.length; i++) {
			Field f = field[i];
			int mod = f.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }
			f.setAccessible(true);
			try {
				map.put(f.getName(), f.get(obj));
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public  static <T> T  map2Obj(Map<String, Object> map, Class<T> beanClass) {    
        if (map == null || map.size()<=0)   
            return null;    
    
		try {
			T obj = beanClass.newInstance();  
			Field[] fields = beanClass.getDeclaredFields();   
			for (Field field : fields) {    
			    int mod = field.getModifiers();    
			    if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
			        continue;    
			    }    
			    //由字符串转换回对象对应的类型
			     if (field != null) {
			         field.setAccessible(true);
			         field.set(obj, map.get(field.getName()));
			     }
			}
			
			return obj;
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}   
        return null;    
    }
}
