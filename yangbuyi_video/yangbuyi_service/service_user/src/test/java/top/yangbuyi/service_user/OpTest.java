/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package top.yangbuyi.service_user;

import top.yangbuyi.service_base.utils.text.StringUtils;
import top.yangbuyi.service_user.utils.WxConstantUtils;

public class OpTest {
    public static void main(String[] args) {
        String getToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid={}" +
                "&secret={}" +
                "&code={}" +
                "&grant_type=authorization_code";
        String format = StringUtils.format(getToken, 1,2, 3);
        System.out.println(format);
    }
}
