package com.ysughw.bootdemo.api.entity;

import java.io.Serializable;
import java.util.Date;

public class CustInfo implements Serializable {
    private Integer clientidx;

    private String clientid;

    private String comment;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Integer getClientidx() {
        return clientidx;
    }

    public void setClientidx(Integer clientidx) {
        this.clientidx = clientidx;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}