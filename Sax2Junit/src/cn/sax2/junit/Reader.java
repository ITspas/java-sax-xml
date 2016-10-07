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
				System.out.println("��ʼ�����ĵ�-----����:"
						+ reader.getEncoding());
				break;
			case Sax2Reader.END_DOCUMENT:
				System.out.println("���������ĵ�");
				break;
			case Sax2Reader.START_TAG:
				System.out.println("��ʼ�����ڵ�-----�ڵ���:" + reader.getName());
				for (Entry<String, String> entry : reader.getAttrs().entrySet()) {
					System.out.println("\t\t�ڵ�����:key:" + entry.getKey()
							+ ",�ڵ�ֵ:" + entry.getValue());
				}
				System.out.println("\t\t�ڵ���ı�:" + reader.nextText());
				break;
			case Sax2Reader.END_TAG:
				System.out.println("���������ڵ�-----�ڵ���:" + reader.getName());
				break;
			default:
				break;
			}
			reader.next();
		}
	}
}
