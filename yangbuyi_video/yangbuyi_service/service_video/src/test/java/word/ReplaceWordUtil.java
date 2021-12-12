/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package word;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReplaceWordUtil {

    private Map<String, String> map = null;

    public void toNewXML(String inputDocPath, String outPutDocPath, Map<String, String> data) {
        map = data;
        File docFile = new File(inputDocPath);
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(docFile);
            Element root = doc.getDocumentElement();
            stepThrough(root);
        } catch (Exception e) {
            System.out.print("遍历替换时出错" + e.getMessage());
        }
        doc2XmlFile(doc, outPutDocPath);
    }

    /**
     * word转XMl
     *
     * @param document document
     * @param filename outPutDocPath
     */
    public static void doc2XmlFile(Document document, String filename) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 遍历执行
     *
     * @param start node
     */
    private void stepThrough(Node start) {
        if (start.getNodeName().equals("#text")) {
            String str = start.getNodeValue();
            int x = str.indexOf("$");
            int y = str.indexOf("@");
            if (x >= 0 && y >= 0 && x < y) {
                String replace = str.substring(x, y) + "@";
                String key = str.substring(x + 1, y);
                String val = queryMap(key);
                // 替换
                String newStr = str.replace(replace, val);
                start.setNodeValue(newStr);
                System.out.println("replace=[" + replace + "], key=[" + key + "] val=[" + val + "], newStr=[" + newStr + "]");
            }
        }
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling()) {
            stepThrough(child);
        }
    }

    private String queryMap(String inputStr) {
        String str = "";
        for (String key : map.keySet()) {
            String val = map.get(key);

            if (inputStr.equals(key)) {
                str = val;
                break;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String inputDocPath = "F:\\疯码\\video\\CloudVideo\\yangbuyi_video\\service\\service_video\\src\\test\\java\\word\\template.xml";
        String outPutDocPath = "F:\\疯码\\video\\CloudVideo\\yangbuyi_video\\service\\service_video\\src\\test\\java\\word\\template.docx";
        Map<String, String> map = new HashMap<>();
        // 填充第一个占位符
        map.put("title", "小黑");
        // 填充第二个占位符
        map.put("age", "123");
        map.put("name", "啊实打实");
        ReplaceWordUtil op = new ReplaceWordUtil();
        op.toNewXML(inputDocPath, outPutDocPath, map);
    }
}