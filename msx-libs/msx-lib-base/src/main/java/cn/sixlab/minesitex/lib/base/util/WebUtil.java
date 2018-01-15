/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/12/28 18:06
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.lib.base.util;


import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    
    public static String readToken(HttpServletRequest request, String jwtHeader, String jwtSecret) {
        //先读取 Header
        String token = request.getHeader(jwtHeader);
    
        //如果没有，再读取 url 参数
        if (StringUtils.isEmpty(token)) {
            String auth = request.getParameter(jwtHeader);
            if(!StringUtils.isEmpty(auth)){
                String uri = request.getRequestURI();
                
                token = DigestUtil.fakeMD5(uri, jwtSecret);
            }
        }
        
        //如果没有，再读取 cookie
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (jwtHeader.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        
        return token;
    }

}
