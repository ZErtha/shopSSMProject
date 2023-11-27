package com.gec.utils;

/**
 * 分页实体类
 */
public class Page {

    /**
     * 每一页开始的行数(当前页数*limit)(因为mysql是从0开始)
     */
    private int offset=0;

    /**
     * 每一页的行数
     */
    private int limit=5;

    /**
     * 总共的数据量
     */
    private int total;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 计算得到总页数
     * @return
     */
    public int getTotalPage() {
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == total % limit)
            totalPage = total / limit;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            totalPage = total / limit + 1;
        return totalPage;
    }

    public int getOffset(int currentPage) {
        if (currentPage!=0)
            this.offset = currentPage*limit-limit;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
