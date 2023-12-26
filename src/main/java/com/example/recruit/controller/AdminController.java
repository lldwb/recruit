package com.example.recruit.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.recruit.common.BaseResponse;
import com.example.recruit.common.ErrorCode;
import com.example.recruit.domain.Admin;
import com.example.recruit.exception.BusinessException;
import com.example.recruit.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lldwb
 * @email 3247187440@qq.com
 * @date 2023/12/25
 * @time 15:14
 * @PROJECT_NAME recruit
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {
    private final AdminService service;

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public BaseResponse login(Admin admin) {
        Admin admin1 = service.getOne(new QueryWrapper<Admin>().eq("adminName", admin.getAdminName()));
        if (admin1 == null || DigestUtil.sha256Hex(admin.getAdminPwd()).equals(admin1.getAdminPwd())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String jwt = JWT.create()
                // 设置签发时间
                .setIssuedAt(DateUtil.date())
                // 设置过期时间
                .setExpiresAt(DateUtil.date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                // 设置用户id
                .setKey(admin1.getAdminId().toString().getBytes())
                // 签名生成JWT字符串
                .sign();
        return success(jwt);
    }

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @PutMapping("/addAdmin")
    public BaseResponse addAdmin(Admin admin) {
        service.save(admin);
        return success(service.getById(admin.getAdminId()));
    }

    /**
     * 根据id返回
     *
     * @param adminId
     * @return
     */
    @GetMapping("/getAminById")
    public BaseResponse<Admin> getAminById(Integer adminId) {
        return success(service.getById(adminId));
    }

    /**
     * 返回管理员列表
     *
     * @param admin
     * @return
     */
        @GetMapping("/getAdmins")
    public BaseResponse<List<Admin>> getAdmins(Admin admin) {
        return success(service.list(new QueryWrapper<Admin>().allEq(BeanUtil.beanToMap(admin))));
    }

    @PutMapping("/updateAdmin")
    public BaseResponse updateAdmin(Admin admin) {
        service.update(admin, new UpdateWrapper<Admin>().eq("adminId", admin.getAdminId()));
        return success();
    }

    @DeleteMapping("/deleteAdmin")
    public BaseResponse deleteAdmin(Integer adminId) {
        Admin admin = new Admin();
        admin.setAdminState(0);
        service.update(admin, new UpdateWrapper<Admin>().eq("adminId", admin.getAdminId()));
        return success();
    }
}
