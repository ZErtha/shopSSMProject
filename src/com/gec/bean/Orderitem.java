package com.gec.bean;

import java.io.Serializable;

/**
 * 订单明细
 * @TableName orderitem
 */
public class Orderitem implements Serializable {
    /**
     * 订单明细id
     */
    private String itemid;

    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 购买总价
     */
    private Double subtotal;

    /**
     * 商品外键:购买的商品的商品id
     */
    private String pid;

    /**
     * 订单id
     * 这个订单明细是属于哪个订单的
     */
    private String oid;

    /**
     * 商品
     * 一个订单明细对应一个商品
     */
    private Product product;


    private static final long serialVersionUID = 1L;

    /**
     * 获取订单明细id
     */
    public String getItemid() {
        return itemid;
    }

    /**
     * 设置订单明细id
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取购买数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置购买数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取购买总价
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * 设置购买总价
     */
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * 获取商品外键:购买的商品的商品id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置商品外键:购买的商品的商品id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取订单id
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置订单id
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Orderitem other = (Orderitem) that;
        return (this.getItemid() == null ? other.getItemid() == null : this.getItemid().equals(other.getItemid()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getSubtotal() == null ? other.getSubtotal() == null : this.getSubtotal().equals(other.getSubtotal()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemid() == null) ? 0 : getItemid().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getSubtotal() == null) ? 0 : getSubtotal().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemid=").append(itemid);
        sb.append(", count=").append(count);
        sb.append(", subtotal=").append(subtotal);
        sb.append(", pid=").append(pid);
        sb.append(", oid=").append(oid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}