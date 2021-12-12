/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wordReplace {
    public static void main(String[] args) {
        aucLotDownLoad();
    }
    public static void aucLotDownLoad() {

        try {
            //获取模板文件
            File demoFile = new File("F:\\template\\template.docx");

            FileInputStream in = new FileInputStream(demoFile);

            XWPFDocument hdt = new XWPFDocument(in);

            //替换读取到的word模板内容的指定字段
            Map<String, String> map = new HashMap<>();
            map.put("$title$", "标题啊");
            map.put("$name$", "ddddd");
            map.put("$age$", "213131");
            List<XWPFTable> paragraphs = hdt.getTables();
            for (int i = 0; i < paragraphs.size(); i++) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    paragraphs.get(i).getRow(i).getCell(i).getText().replace(entry.getKey(), entry.getValue());
                }
            }


//            //输出word内容文件流，提供下载
//
//            response.setContentType("application/x-msdownload");

            String name = java.net.URLEncoder.encode("F:\\template\\成交确认书_" + ".docx", "UTF8");

            name = new String((name).getBytes("UTF-8"), "ISO-8859-1");

//            response.addHeader("Content-Disposition", "attachment; filename*=utf-8'zh_cn'" + name);

            OutputStream os = new FileOutputStream(name);
            hdt.write(os);
            close(os);
            close(in);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private static void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
