package com.gec.bean;

import java.io.Serializable;

/**
 * 类别
 * 商品分类
 * @TableName category
 */
//目标类实现Serializable接口
public class Category implements Serializable {
    /**
     * 类别id
     */
    private String cid;

    /**
     * 类别名称
     */
    private String cname;

    /**
     * 序列化的版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取类别id
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置类别id
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取类别名称
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置类别名称
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            // 非空性
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Category other = (Category) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getCname() == null ? other.getCname() == null : this.getCname().equals(other.getCname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getCname() == null) ? 0 : getCname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", cname=").append(cname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}