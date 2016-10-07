package cn.sax2.junit;

import java.util.Map.Entry;

import org.junit.Test;

import cn.xml.resolve.Sax2Reader;

public class Reader {
	@Test
	public void test1() throws Exception {
		Sax2Reader reader = new Sax2Reader("1.xml");
		while (reader.getState() != Sax2Reader.END_DOCUMENT) {
			switch (reader.getState()) {
			case Sax2Reader.START_DOCUMENT:
				System.out.println("开始解析文档-----编码:"
						+ reader.getEncoding());
				break;
			case Sax2Reader.END_DOCUMENT:
				System.out.println("结束解析文档");
				break;
			case Sax2Reader.START_TAG:
				System.out.println("开始解析节点-----节点名:" + reader.getName());
				for (Entry<String, String> entry : reader.getAttrs().entrySet()) {
					System.out.println("\t\t节点属性:key:" + entry.getKey()
							+ ",节点值:" + entry.getValue());
				}
				System.out.println("\t\t节点后文本:" + reader.nextText());
				break;
			case Sax2Reader.END_TAG:
				System.out.println("结束解析节点-----节点名:" + reader.getName());
				break;
			default:
				break;
			}
			reader.next();
		}
	}
}
