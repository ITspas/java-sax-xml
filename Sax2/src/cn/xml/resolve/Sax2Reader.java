package cn.xml.resolve;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sax2解析XML文档
 * 
 * @author Jsc
 * 
 */
public class Sax2Reader {
	// Const Field
	public static final int START_DOCUMENT = 1;
	public static final int END_DOCUMENT = 2;
	public static final int START_TAG = 3;
	public static final int END_TAG = 4;
	public static final int DOCTYPEORNOTE = 5;

	// Field
	private InputStream inputStream;
	private ByteBuffer buffer;
	private String name;
	private String text;
	private int state;
	private String encoding;
	private Attributes attrs;
	private int line = 0;

	/**
	 * 构造函数
	 * 
	 * @param path
	 * @throws Exception
	 */
	public Sax2Reader(String path) throws Exception {
		this.inputStream = Sax2Reader.class.getClassLoader()
				.getResourceAsStream(path);
		this.buffer = ByteBuffer.allocate(256);
		this.attrs = new Attributes();
		this.removeHeader();
		this.resolve();
	}

	/**
	 * 获取解析状态
	 * 
	 * @return
	 */
	public int getState() {
		return this.state;
	}
	
	/**
	 * 获取编码
	 * @return
	 */
	public String getEncoding(){
		return this.encoding;
	}

	/**
	 * 获取节点名
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 获取节点后文本
	 * 
	 * @return
	 */
	public String nextText() {
		return this.text;
	}

	/**
	 * 节点属性集合
	 * 
	 * @return
	 */
	public Attributes getAttrs() {
		return this.attrs;
	}

	/**
	 * 根据属性名称获取属性值
	 * 
	 * @param name
	 * @return
	 */
	public String getAttrOfValue(String name) {
		return this.attrs.get(name);
	}

	/**
	 * 解析后面代码
	 * 
	 * @throws Exception
	 */
	public void next() throws Exception {
		this.resolve();
	}

	/**
	 * 移除前空白
	 * 
	 * @throws Exception
	 */
	private void removeHeader() throws Exception {
		while ((line = this.inputStream.read()) > 0) {
			if ("<".equals(Character.toString((char) line)))
				break;
		}
	}

	/**
	 * 解析数据
	 * 
	 * @throws Exception
	 */
	private void resolve() throws Exception {
		this.state = Sax2Reader.START_TAG;
		this.name = this.text = "";
		this.attrs.clear();
		this.buffer.clear();
		while ((line = this.inputStream.read()) > 0) {
			if ("<".equals(Character.toString((char) line)))
				break;
			this.buffer.put((byte) line);
		}
		String ctn = new String(buffer.array(),0,buffer.position(),encoding==null?Charset.defaultCharset().toString():encoding).trim();
		if(ctn.length()==0){
			this.state = Sax2Reader.END_DOCUMENT;
			return;
		}
		this.name = ctn.replaceAll("(\\?|!|(\\s.+=.+)+|>|/)", "");
		Matcher m = Pattern.compile("(\\s(\\w|.+?)=(\\\"|').+?(\\\"|'))").matcher(ctn);
		while(m.find()){
			String[] strs = m.group(1).split("=");
			this.attrs.put(strs[0].trim(), strs[1].replaceAll("\\\"",""));
		}
		this.text = ctn.replaceAll(".+?>","");
		if(ctn.startsWith("?")){
			for (Entry<String, String> entry : attrs.entrySet()) {
				if("encoding".equals(entry.getKey().toLowerCase()))
					this.encoding = entry.getValue();
			}
			this.state = Sax2Reader.START_DOCUMENT;
		}
		if(ctn.startsWith("!"))
			this.state = Sax2Reader.DOCTYPEORNOTE;
		if(ctn.startsWith("/"))
			this.state = Sax2Reader.END_TAG;
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if (ctn.length() == 0) {
//			this.state = Sax2Reader.END_DOCUMENT;
//			return;
//		}
//		this.name = ctn.replaceAll("(\\?|!|(\\s.+=.+)+|>)", "");
//		Matcher matcher = Pattern.compile("(\\s.+=.+)").matcher(
//						new String(this.buffer.array(),"utf-8").trim());
//		while (matcher.find()) {
//			System.out.println(matcher.group(1));
//			String[] strs = matcher.group(1).split("=");
//			this.attrs.put(strs[0], strs[1].replaceAll("('|\")", ""));
//		}
//		this.text = new String(this.buffer.array(),"utf-8").trim().replaceAll("(.+)>", "");
//		if (ctn.startsWith("?")) {
//			this.state = Sax2Reader.START_DOCUMENT;
//			this.encoding = this.getAttrOfValue("encoding");
//		}
//		if (ctn.startsWith("!"))
//			this.state = Sax2Reader.DOCTYPEORNOTE;
//		if (ctn.startsWith("/"))
//			this.state = Sax2Reader.END_TAG;
	}
}
