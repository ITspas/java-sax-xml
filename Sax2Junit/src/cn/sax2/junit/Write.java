package cn.sax2.junit;

import java.io.FileOutputStream;

import org.junit.Test;

import cn.xml.resolve.Attributes;
import cn.xml.resolve.Sax2Writer;

public class Write {
	@Test
	public void app1() throws Exception{
		Attributes attributes = new Attributes();
		attributes.put("name", "tom");
		attributes.put("age", "11");
		FileOutputStream os = new FileOutputStream("src\\2.xml");
		Sax2Writer writer = new Sax2Writer(os,"utf-8");
		writer.writeDocument();
			writer.writeStartTag("persons", null, null);
				for (int i = 0; i <3; i++) {
					writer.writeStartTag("person", attributes, null);
					writer.writeStartTag("height", null,"10.1");
					writer.writeEndTag("height");
					writer.writeEndTag("person");
				}
			writer.writeEndTag("persons");
		writer.writeEndDocument();
	}
}
