/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package excel; import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: yangbuyi_video
 * @ClassName: excel.StudentData
 * @create: 2021-09-08 12:43
 * @author: Yang Shuai
 * @since： JDK1.8
 * @StudentData: $
 **/


@Data
public class StudentData {
    @ExcelProperty(value = "学号",index = 0)
    private Integer no;
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
}
