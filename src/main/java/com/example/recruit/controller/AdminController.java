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
    @PutMapping("/add")
    public BaseResponse addAdmin(Admin admin) {
        service.save(admin);
        return success(service.getById(admin.getAdminId()));
    }

    /**
     * 根据id返回
     *
     * @param id
     * @return
     */
    @GetMapping("/getId")
    public BaseResponse<Admin> getAminById(Integer id) {
        return success(service.getById(id));
    }

    /**
     * 返回管理员列表
     *
     * @param admin
     * @return
     */
    @GetMapping("/getList")
    public BaseResponse<List<Admin>> getList(Admin admin) {
        return success(service.list(new QueryWrapper<Admin>().allEq(BeanUtil.beanToMap(admin,true,true))));
    }

    @PostMapping("/update")
    public BaseResponse update(Admin admin) {
        service.update(admin, new UpdateWrapper<Admin>().eq("admin_id", admin.getAdminId()));
        return success();
    }

    @DeleteMapping("/delete")
    public BaseResponse delete(Integer adminId) {
        Admin admin = new Admin();
        admin.setAdminState(0);
        admin.setAdminId(adminId);
        service.update(admin, new UpdateWrapper<Admin>().eq("admin_id", admin.getAdminId()));
        return success();
    }
}
