package com.gec.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单的实体类
 * @TableName orders
 */
public class Orders implements Serializable {
    /**
     * 订单id
     */
    private String oid;

    /**
     * 下单时间
     */
    private Date ordertime;

    /**
     * 订单总金额
     */
    private Double total;

    /**
     * 订单状态
     * 0：未支付,去付款，
     * 1：已付款,催单，
     * 2：确定收货，
     * 4：订单完成，查看订单详情
     * 5：订单已取消
     */
    private Integer state=-1;

    /**
     * 收件人地址
     */
    private String address;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 收件人手机号
     */
    private String telephone;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 评论
     */
    private String assess;

    /**
     * 订单明细
     * 一个订单包含多个订单明细
     */
    private List<Orderitem> orderItems = new ArrayList<>();

    private static final long serialVersionUID = 1L;

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

    /**
     * 获取下单时间
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * 设置下单时间
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 获取订单总金额
     */
    public Double getTotal() {
        return total;
    }

    /**
     * 设置订单总金额
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * 获取订单状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取收件人地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收件人地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取收件人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收件人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取收件人手机号
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置收件人手机号
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取用户uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置用户uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取订单评价
     */
    public String getAssess() {
        return assess;
    }

    /**
     * 设置订单评价
     */
    public void setAssess(String assess) {
        this.assess = assess;
    }

    /**
     * 获取订单明细列表
     */
    public List<Orderitem> getOrderItems() {
        return orderItems;
    }

    /**
     * 设置订单明细列表
     */
    public void setOrderItems(List<Orderitem> orderItems) {
        this.orderItems = orderItems;
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
        Orders other = (Orders) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getOrdertime() == null ? other.getOrdertime() == null : this.getOrdertime().equals(other.getOrdertime()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getAssess() == null ? other.getAssess() == null : this.getAssess().equals(other.getAssess()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getOrdertime() == null) ? 0 : getOrdertime().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getAssess() == null) ? 0 : getAssess().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", ordertime=").append(ordertime);
        sb.append(", total=").append(total);
        sb.append(", state=").append(state);
        sb.append(", address=").append(address);
        sb.append(", name=").append(name);
        sb.append(", telephone=").append(telephone);
        sb.append(", uid=").append(uid);
        sb.append(", assess=").append(assess);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}