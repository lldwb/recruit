package com.example.recruit.controller.interceptor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recruit.common.ErrorCode;
import com.example.recruit.domain.User;
import com.example.recruit.exception.BusinessException;
import com.example.recruit.mapper.UserMapper;
import com.example.recruit.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 进行登录会话的检测
 *
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2024/1/4
 * @time 14:56
 * @PROJECT_NAME recruit
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    /**
     * 在调用 Controller 的请求方法之前执行，如果此方法返回false，则请求就不会继续往下执行
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle：在调用 Controller 的请求方法之前执行，如果此方法返回false，则请求就不会继续往下执行");
        String userId = request.getParameter("userId");
        String userPhone = request.getParameter("userPhone");
        String adminId =  request.getParameter("adminId");
        if (adminId != null && !"".equals(adminId)) {

        }else{User user = null;
            if (userId != null && !"".equals(userId)){
                user = userService.getById(userId);
            }
            if (userPhone != null && !"".equals(userPhone)) {
                user = userService.getOne(new QueryWrapper<User>().eq("user_phone", userPhone));
            }
            log.info("user：{}",user);
            if (user != null && user.getUserState() < 1) {
                throw new BusinessException(ErrorCode.NO_AUTH);
//                return false;
            }
        }


//        String userId = request.getParameter("userId");
//        String adminId =  request.getParameter("adminId");
//        // 管理员idJWT解析(用于关闭用户id的解析)(暂时不开启解析JWT，后面开启对管理员的JWT解析)
//        if (adminId != null && !"".equals(adminId)) {
//
//        }
//        // 用户idJWT解析
//        else if (userId != null && !"".equals(userId)) {
//            // 检测会话是否过期
//            JWTValidator.of(userId).validateDate(DateUtil.date());
//            final JWT jwt = JWTUtil.parseToken(userId);
//
//            jwt.getHeader(JWTHeader.TYPE);
//            request.setAttribute("userId", jwt.getPayload("userId"));
//        }

//        String userPhone = request.getParameter("userPhone");
//        if (userPhone != null && !"".equals(userPhone)) {
//            request.setAttribute("userPhone",AES.decryptStr(userPhone, CharsetUtil.CHARSET_UTF_8));
//            request.getAttribute("");
//            request.getParameter("");
//        }
        return true;
    }
}

