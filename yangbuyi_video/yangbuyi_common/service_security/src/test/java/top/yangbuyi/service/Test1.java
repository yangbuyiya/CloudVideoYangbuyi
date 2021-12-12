/*------------------------------------------------------------------------------
 -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 -----------------------------------------------------------------------------*/

package top.yangbuyi.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.common_utils.utils.MD5;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_security.security.TokenManager;

/**
 * @program: yangbuyi_video
 * @ClassName: Test1
 * @create: 2021-10-03 15:04
 * @author: Yang Shuai
 * @since： JDK1.8
 * @Test1: $
 **/


public class Test1 {

    @Test
    public void t () {

        final TokenManager manager = new TokenManager();
        final String token = manager.createToken("杨不易");
        System.out.println(token);
        /*-----------------------------------*/
        final String user = manager.getUserFromToken(token);
        System.out.println(user);

        String encrypt = MD5.encrypt("111111");
        System.out.println(encrypt);

    }

}
