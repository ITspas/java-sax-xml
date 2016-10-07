package cn.xml.resolve;

import java.io.OutputStream;
import java.util.Map.Entry;

/**
 * Sax2Write
 * @author Jsc
 *
 */
public class Sax2Writer {
	//Field
	private OutputStream outputStream;
	private String encoding = "utf-8";
	private StringBuffer buffer = new StringBuffer();

	/**
	 * 构造方法
	 * @param os
	 */
	public Sax2Writer(OutputStream os) {
		this.outputStream = os;
	}

	/**
	 * 构造方法
	 * @param os
	 * @param encoding
	 */
	public Sax2Writer(OutputStream os, String encoding) {
		this.outputStream = os;
		this.encoding = encoding;
	}

	/**
	 * 设置编码
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 写开始文档
	 * @throws Exception
	 */
	public void writeDocument() throws Exception {
		this.outputStream.write(("<?xml version=\"1.0\" encoding=\""
				+ this.encoding + "\" ?>").getBytes(this.encoding));
	}

	/**
	 * 写开始标签
	 * @param name
	 * @param attrs
	 * @param text
	 * @throws Exception
	 */
	public void writeStartTag(String name, Attributes attrs,
			String text) throws Exception {
		this.buffer.setLength(0);
		this.buffer.append(String.format("<%s ", name));
		if(attrs!=null)
		for (Entry<String, String> entry : attrs.entrySet()) {
			this.buffer.append(String.format("%s=\"%s\" ", entry.getKey(),
					entry.getValue()));
		}
		this.buffer.append(String.format(">%s", text == null ? "" : text));
		this.outputStream.write(this.buffer.toString().trim()
				.getBytes(this.encoding));
	}

	/**
	 * 写结束标签
	 * @param name
	 * @throws Exception
	 */
	public void writeEndTag(String name) throws Exception {
		this.outputStream.write(String.format("</%s>", name).getBytes(
				this.encoding));
	}

	/**
	 * 写结束文档
	 * @throws Exception
	 */
	public void writeEndDocument() throws Exception {
		this.outputStream.flush();
		this.outputStream.close();
	}
}
