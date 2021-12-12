/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/


import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @program: yangbuyi_video
 * @ClassName: wordRepl
 * @create: 2021-09-25 22:23
 * @author: Yang Shuai
 * @since： JDK1.8
 * @wordRepl: $
 **/

public class wordRepl {

    @Test
    public void test () {
        //加载Word文档


    }


    @Test
    public void testWrite () throws Exception {
        Map<String, String> content = new HashMap<String, String>();
        Map<String, String> label = new HashMap<String, String>();
        label.put("Title", "测试替换文字");
        content.put("${val1}", "丫头");
        content.put("${val2}", "女");
        label.put("${Name}", "刘志宝贝");
        String srcPath = "C:\\Users\\16927\\Desktop\\template.docx";
        String destPath = "C:\\Users\\16927\\Desktop\\" + UUID.randomUUID().toString() + ".docx";
        System.out.println(destPath);
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));

            List<XWPFParagraph> list = document.getParagraphs();
            //基本内容替换
            for (int i = 0; i < list.size(); i++) {
                for (Map.Entry<String, String> e : label.entrySet()) {
                    String key = list.get(i).getParagraphText();
                    if (key.indexOf("$") != -1) {
                        key = key.substring(key.indexOf("$"), key.lastIndexOf("}"));
                    }
                    if (key.equals(e.getKey())) {
                        List<XWPFRun> runs = list.get(i).getRuns();
                        //删除原来内容
                        for (int j = runs.size() - 1; j >= 0; j--) {
                            list.get(i).removeRun(j);
                        }


                        //创建新内容
                        XWPFRun paragraphRun = list.get(i).createRun();
                        paragraphRun.setText(e.getValue(),0);
                        paragraphRun.setBold(true);
                        paragraphRun.setFontFamily(runs.get(i).getFontFamily());
                        paragraphRun.setFontSize(runs.get(i).getFontSize());
//                        paragraphRun.set
                        System.out.println(list.get(i).getParagraphText() + ":新的:" + e.getValue());
                    }
                }

            }

            /**
             * 操作表格内容
             */

            Iterator<XWPFTable> it = document.getTablesIterator();
            //表格内容替换添加
            while (it.hasNext()) {
                XWPFTable table = (XWPFTable) it.next();
                int rcount = table.getNumberOfRows();
                System.out.println(rcount);
                for (int i = 0; i < rcount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> e : content.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                //删除原来内容
                                System.out.println(cell.getText());
                                cell.removeParagraph(0);

                                //写入新内容
                                cell.setText(e.getValue());

                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
