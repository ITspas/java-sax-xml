package cn.xml.resolve;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 属性集合
 * @author Jsc
 *
 */
public class Attributes{
	//Field
	Map<String, String> attrs = new HashMap<String, String>();
	
	/**
	 * 添加属性
	 * @param key
	 * @param value
	 */
	public void put(String key,String value){
		this.attrs.put(key, value);
	}
	
	/**
	 * 回去属性
	 * @param key
	 * @return
	 */
	public String get(String key){
		return this.attrs.get(key);
	}
	
	/**
	 * 属性集合
	 * @return
	 */
	public Set<Entry<String, String>> entrySet(){
		return this.attrs.entrySet();
	}
	
	/**
	 * 清空数据
	 */
	public void clear(){
		this.attrs.clear();
	}
}
