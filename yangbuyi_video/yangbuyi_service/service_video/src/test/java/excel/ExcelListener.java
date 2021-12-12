/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: yangbuyi_video
 * @ClassName: ExcelListener
 * @create: 2021-09-09 00:00
 * @author: Yang Shuai
 * @since： JDK1.8
 * @ExcelListener: $
 **/

@Slf4j
public class ExcelListener extends AnalysisEventListener<StudentData> {


    /**
     * 调用
     * 一行一行读取数据
     *
     * @param studentData     学生数据
     * @param analysisContext 分析上下文
     */
    @Override
    public void invoke (StudentData studentData, AnalysisContext analysisContext) {
        log.info("当前行:"+ studentData.toString());
    }

    /**
     * 调用头地图
     * 读取表头
     *
     * @param headMap 头图
     * @param context 上下文
     */
    @Override
    public void invokeHeadMap (Map<Integer, String> headMap, AnalysisContext context) {
        log.info("表头:" + headMap);
    }

    /**
     * 所有的数据读取完毕之后会自动调用
     *
     * @param analysisContext 分析上下文
     */
    @Override
    public void doAfterAllAnalysed (AnalysisContext analysisContext) {
        log.info("--------------------------------------------------");
        log.info("----------------------读取完毕----------------------");
        log.info("--------------------------------------------------");
    }
}
