/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package word;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import top.yangbuyi.service_base.utils.text.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换文档的占位符,保留占位符原有样式
 * 此处只用于demo,请根据业务需要,定制成你喜欢的样子
 */

public class DocxUtils {


    /**
     * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
     *
     * @throws Exception
     */
    private static void templateWrite(String filePath, String outFilePath, Map<String, String> params) throws Exception {
        InputStream is = new FileInputStream(filePath);
        XWPFDocument doc = new XWPFDocument(is);
        Iterator<XWPFParagraph> paragraphsIterator = doc.getParagraphsIterator();
        System.out.println("参数:" + params);
        while (paragraphsIterator.hasNext()) {
            //替换段落里面的变量
            replaceInPara(paragraphsIterator.next(), params);
        }
        OutputStream os = new FileOutputStream(outFilePath);
        doc.write(os);
        close(os);
        close(is);
    }

    private static XWPFDocument replaceTemplateKeyFromParams(String templatePath, Map<String, String> params) throws Exception {
        File tmpFile = ResourceUtils.getFile(templatePath);
        InputStream inputStream = new FileInputStream(templatePath);
        XWPFDocument xwpfDocument = new XWPFDocument(OPCPackage.open(tmpFile));
        Iterator<XWPFParagraph> paragraphsIterator = xwpfDocument.getParagraphsIterator();
        while (paragraphsIterator.hasNext()) {
            replaceInPara(paragraphsIterator.next(), params);
        }
        close(inputStream);
        return xwpfDocument;
    }

    /**
     * 替换段落里面的变量
     *
     * @param paragraph 要替换的段落
     * @param params    参数
     */
    private static void replaceInPara(XWPFParagraph paragraph, Map<String, String> params) {
        System.out.println("替换的段落:" + paragraph);
        System.out.println(paragraph.getParagraphText());
        if (!matcher(paragraph.getParagraphText()).find()) {
            return;
        }

        List<XWPFRun> runs = paragraph.getRuns();
        if (CollectionUtils.isEmpty(runs)) {
            return;
        }


        int index = 0;
        do {
            int fontSize = 10;
            String fontFamily = "";

            // 标记占位符开始的run出现
            boolean begin = false;
            // 记录占位符第一个run的位置
            int logRunPos = 0;
            // 包含占位符runs可能跟非占位符的run搞在一起了
            StringBuilder containKeyRunTextBuilder = new StringBuilder();

            // 提取变量key,形如:${xxx}, 一个变量key可能包含在不同的run里面,所以循环操作,同时把run的样式拿出来,然后删除这个包含key的run
            for (int i = 0; i < runs.size(); ) {
                XWPFRun xwpfRun = runs.get(i);
                String runText = xwpfRun.toString();
                // 一个run可能会包含多个占位符,不会写了,报个错吧
                if ((" " + runText + " ").split("}").length > 2) {
                    throw new RuntimeException("不支持同一个run包含多个变量,建议对文档执行一次替换变量操作");
                }
                if (runText.contains("${")) {
                    // 占位符出现了
                    logRunPos = i;
                    fontSize = xwpfRun.getFontSize();
                    fontFamily = xwpfRun.getFontFamily();
                    containKeyRunTextBuilder.append(runText);
                    paragraph.removeRun(i);
                    begin = true;
                } else if (begin) {
                    containKeyRunTextBuilder.append(runText);
                    paragraph.removeRun(i);
                    if (runText.contains("}")) {
                        // 找到了一个完整的占位符
                        break;
                    }
                } else {
                    // 占位符没出现
                    i++;
                    index++;
                }
            }

            // 前面的for循环已经把包含占位符run全部删除了,此处插回去
            Matcher matcher = matcher(containKeyRunTextBuilder.toString());
            if (matcher.find()) {
                String group = matcher.group(1);
                String value = params.get(group);
                if (StringUtils.isBlank(value)) {
                    value = "[key没有对应的值]";
                }
                String s = matcher.replaceFirst(value);
                XWPFRun run = paragraph.insertNewRun(logRunPos);
                run.setText(s);
                run.setFontSize(fontSize);
                run.setFontFamily(fontFamily);
            }

            // 此处为了处理一个段落包含多个占位符
        } while (index < runs.size());

    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private static Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
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

    public static void main(String[] args) throws Exception {

        Map<String, String> params = new HashMap<>();

        //当前时间
        params.put("${title}", "测试");
        params.put("${age}", "123");
        params.put("${name}", "芜湖");

        String wordSrcPath = "F:\\template\\template.docx";
        String wordDestPath = "F:\\template\\" + "new.docx";
        templateWrite(wordSrcPath, wordDestPath, params);
    }

}