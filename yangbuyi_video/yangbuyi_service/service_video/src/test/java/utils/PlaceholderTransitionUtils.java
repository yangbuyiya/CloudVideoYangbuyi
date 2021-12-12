/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xwpf.usermodel.*;
import top.yangbuyi.service_base.utils.text.StringUtils;

import java.io.*;
import java.util.*;


public class PlaceholderTransitionUtils {
    private static final Logger logger = LogManager.getLogger(PlaceholderTransitionUtils.class);

    public static void main(String[] args) {
        String file = "F:\\template\\contractDetectionBookTemplet.docx";

        Map<String,String> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("contractNumber", "YBY-001");
        stringObjectHashMap.put("detectTypeNumber", "YBYBH-PPP");
        stringObjectHashMap.put("contractClient", "丫头");
        stringObjectHashMap.put("addressOrIdCard", "湖南此处");
        stringObjectHashMap.put("detectCompany", "啊啊啊啊");

        File file1 = PlaceholderTransitionUtils.placeholderTransition2(new File(file), stringObjectHashMap);

        System.out.println(file1.getAbsolutePath());



    }

    public static File placeholderTransition(File file, Map<String, String> mapData) {

        // 替换占位符
        Map<String, String> map = getItemsMap(mapData);
        Map<String, String> headMap = getItemsHeadMap(mapData);
        logger.info("map size----" + map.size());
        try {
            File file1 = replaceText(file, map, headMap);
            return file1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File placeholderTransition2(File file, Map<String, String> mapData) {

        try {
            File file1 = replaceText(file, mapData, new HashMap<>());
            return file1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param file word模板数据源
     * @param map  关键字键值对映射
     */
    public static File replaceText(File file, Map<String, String> map, Map<String, String> headMap) {
        logger.info("replaceText start----");
        try {
            InputStream is = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(is);
            // 1替换页眉、页脚指定文字
            String textHeader;
            List<XWPFHeader> headerList = document.getHeaderList();

            for (XWPFHeader header : headerList) {
                List<XWPFParagraph> paras = header.getParagraphs();
                for (XWPFParagraph o : paras) {
                    List<XWPFRun> runs = o.getRuns();
                    for (int i = 0, runSie = runs.size(); i < runSie; i++) {
                        textHeader = runs.get(i).getText(runs.get(i).getTextPosition());
                        if (textHeader != null) {
                            for (String keyHeader : headMap.keySet()) {
                                textHeader = textHeader.trim();
                                if (!(StringUtils.isEmpty(headMap.get(keyHeader))) && textHeader.equals(keyHeader)) {
                                    runs.get(i).setText(headMap.get(keyHeader) + "  ", 0);
                                }
                            }
                        }
                    }
                }
            }
            //2. 替换段落中的指定文字
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            String text;
            Set<String> set;
            XWPFParagraph paragraph;
            List<XWPFRun> run;
            String key;
            while (itPara.hasNext()) {
                paragraph = itPara.next();
                set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    key = iterator.next();
                    run = paragraph.getRuns();
                    for (int i = 0, runSie = run.size(); i < runSie; i++) {
                        text = run.get(i).getText(run.get(i).getTextPosition());
                        if (text != null && text.equals(key)) {
                            run.get(i).setText(map.get(key), 0);
                        }
                    }
                }
            }
            //3. 替换表格中的指定文字
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            XWPFTable table;
            int rowsCount;
            while (itTable.hasNext()) {
                table = itTable.next();
                rowsCount = table.getNumberOfRows();
                for (int i = 0; i < rowsCount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        String cellText = cell.getText();
                        for (Map.Entry<String, String> e : map.entrySet()) {
                            if (cellText.equals(e.getKey())) {
                                if ("chou".equals(cellText) || "song".equals(cellText)) {
                                    cell.removeParagraph(0);
                                    cell.addParagraph().setAlignment(ParagraphAlignment.CENTER);
                                    XWPFRun ctRun = cell.getParagraphs().get(0).createRun();
                                    ctRun.setText(e.getValue());
                                    ctRun.setFontFamily("仿宋_GB2312");
                                    ctRun.setFontSize(10);
                                } else {
                                    cell.removeParagraph(0);
                                    cell.addParagraph().setAlignment(ParagraphAlignment.CENTER);
                                    XWPFRun ctRun = cell.getParagraphs().get(0).createRun();
                                    ctRun.setText(e.getValue());
                                    ctRun.setFontFamily("仿宋_GB2312");
                                    ctRun.setFontSize(30);
                                    ctRun.setTextPosition(1);
                                }
                            }
                        }
                    }
                }
            }

            //最终确定保存的目录
            File printDir = new File("F:\\template");
            if (!printDir.exists() || !printDir.isDirectory()) {
                printDir.mkdirs();
            }

            String fileName = file.getName();

            String newPDFPath = printDir.getPath() + File.separator + fileName;
            File out = new File(newPDFPath);
            if (out.exists()) {
                out.delete();
            }

            OutputStream fos = new FileOutputStream(out);
            //3.输出流
            document.write(fos);
            is.close();
            fos.close();
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 占位符替换map
     *
     * @return
     */
    public static Map<String, String> getItemsMap(Map<String, String> mapData) {

        Map<String, String> map = new HashMap<>();
        String song = "送样（/）";
        String chou = "抽样（/）";
        Object sampleAcquisitionMode = getValue(mapData, "sampleAcquisitionMode");
        String toFactoryAddress = getValue(mapData, "toFactoryAddress") == null ? "" : getValue(mapData, "toFactoryAddress").toString();//检验地点
        if (sampleAcquisitionMode != null) {
            Integer integer = Integer.valueOf(sampleAcquisitionMode.toString());
            if (integer == 0) {//赴厂
//                chou = "抽样（√）";
                song = "送样（/）";
            } else if (integer == 1) {//来样
                toFactoryAddress = "本院实验室";
                song = "送样（√）";
            }
        }
        Object samplingNumber = getValue(mapData, "samplingNumber");//抽样单编号
        if (samplingNumber != null && !samplingNumber.toString().equals("")) {
            chou = "抽样（√）";
        }

        map.put("song", song);
        map.put("chou", chou);
        map.put("doc_address", toFactoryAddress);//检验地点
        map.put("doc_reportnum", getValue(mapData, "reportNumber"));     //报告编号
        map.put("doc_jywtf", getValue(mapData, "contractClient"));         //委 托 方
        map.put("doc_ypmc", getValue(mapData, "sampleName"));          //样品名称
        map.put("doc_xhgg", getValue(mapData, "sampleModelSpecification"));          //型号规格
        map.put("doc_jylb", getValue(mapData, "detectType"));          //检验类别
        map.put("doc_ypbh", getValue(mapData, "sampleNumber"));          //样品编号
        map.put("doc_sb", getValue(mapData, "sampleTrademark"));            //商标
        map.put("doc_wtfdz", getValue(mapData, "addressOrIdCard"));         //委托方地址
        map.put("doc_ph", getValue(mapData, "sampleModelSpecification"));          //规格型号
        map.put("doc_scdw", getValue(mapData, "manufactureCompany"));          //生产单位
        map.put("doc_sjdw", getValue(mapData, "detectCompany"));          //受检单位
        map.put("doc_cydw", getValue(mapData, "samplingCompany"));          //抽样单位
        map.put("doc_cydd", getValue(mapData, "samplingAddress"));          //抽样地点
        map.put("doc_cyrq", getValue(mapData, "samplingDate"));          //抽样日期
        map.put("doc_syrq", getValue(mapData, "sampleStoreInDate"));          //到样日期
        map.put("doc_cydbh", getValue(mapData, "samplingNumber"));         //抽样单编号
        map.put("doc_scrq", getValue(mapData, "sampleProductionDate"));          //生产日期
        //样品数量
        Object sampleQuantityObj = getValue(mapData, "sampleQuantity");
        if (sampleQuantityObj == null) {
            map.put("doc_ypsl", getValue(mapData, "sampleList"));
        } else {
            int sampleQuantity = Integer.parseInt(sampleQuantityObj.toString());
            if (sampleQuantity == 0) {
                map.put("doc_ypsl", getValue(mapData, "sampleList"));
            } else {
                map.put("doc_ypsl", getValue(mapData, "sampleQuantity") + "" + getValue(mapData, "sampleUnit"));
            }
        }
//        map.put("doc_ypsl",getValue(mapData,"sampleQuantity")+""+getValue(mapData,"sampleUnit"));          //样品数量
        map.put("doc_cyjs", getValue(mapData, "samplingBase"));          //抽样基数
        map.put("doc_jyrq", getValue(mapData, "startTestDate"));          //检验日期
        map.put("doc_jyxm", getValue(mapData, "content"));          //检验项目
        map.put("doc_jyyj", getValue(mapData, "sampleTestBasis"));          //检验依据
//        map.put("doc_jyjl","123");          //检验结论
        return map;

    }


    /**
     * 页眉替换map
     *
     * @return
     */
    public static Map<String, String> getItemsHeadMap(Map<String, String> mapData) {

        Map<String, String> map = new HashMap<>();
        map.put("header_reportnum", getValue(mapData, "reportNumber"));  //报告编号
        map.put("header_ypbh", getValue(mapData, "sampleNumber"));       //样品编号

        return map;

    }

    private static <T> T getValue(Map<String, String> map, String key) {

        Object val = map.get(key);

        if (val == null) {
            return null;
        }

        return (T) val;
    }

}
