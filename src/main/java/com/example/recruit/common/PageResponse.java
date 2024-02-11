package com.example.recruit.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> extends BaseResponse<T> implements Serializable {
    /**
     * 当前页
     */
    private Long pageNum;
    /**
     * 获取每页显示条数
     */
    private Long pageSize;
    /**
     * 当前分页总页数
     */
    private Long pages;
    /**
     * 当前满足条件总行数
     */
    private Long total;
    public PageResponse(int code, T data, String message, IPage page) {
        super(code,data,message,"");
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
        this.pages = page.getPages();
        this.total = page.getTotal();
    }
}
