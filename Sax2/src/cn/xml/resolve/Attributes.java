package cn.xml.resolve;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ���Լ���
 * @author Jsc
 *
 */
public class Attributes{
	//Field
	Map<String, String> attrs = new HashMap<String, String>();
	
	/**
	 * �������
	 * @param key
	 * @param value
	 */
	public void put(String key,String value){
		this.attrs.put(key, value);
	}
	
	/**
	 * ��ȥ����
	 * @param key
	 * @return
	 */
	public String get(String key){
		return this.attrs.get(key);
	}
	
	/**
	 * ���Լ���
	 * @return
	 */
	public Set<Entry<String, String>> entrySet(){
		return this.attrs.entrySet();
	}
	
	/**
	 * �������
	 */
	public void clear(){
		this.attrs.clear();
	}
}
