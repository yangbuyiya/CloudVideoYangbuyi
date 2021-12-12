/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package excel;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @program: yangbuyi_video
 * @ClassName: WriteExcel
 * @create: 2021-09-08 12:46
 * @author: Yang Shuai
 * @since： JDK1.8
 * @WriteExcel: $
 **/


public class WriteExcel {
    public static void main (String[] args) {
        String fileName = "D:\\01.xlsx";
        ArrayList<StudentData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentData studentData = new StudentData();
            studentData.setNo(i);
            studentData.setName("fmjava" + i);
            list.add(studentData);
        }
        /**
         * 1.fileName 文件路径与名称
         * 2.StudentData.class  模型的字节码
         * 3.sheet 名称
         * 4.doWrite  要写的数据  是一个list
         */
        EasyExcel.write(fileName, StudentData.class).sheet("学生").doWrite(list);
    }
}
