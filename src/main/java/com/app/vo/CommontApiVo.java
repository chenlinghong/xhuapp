package com.app.vo;/*
 *@Author:dxlin
 *@Description：
 *@Date: 2018-3-
 */

import com.app.entity.Commont;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "commontApiVo")
@Scope(value = "prototype")
public class CommontApiVo {
    private int code;
    private String message;
    private Commont commont;
    private List<Commont> commontList;

    public CommontApiVo(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Commont getCommont() {
        return commont;
    }

    public void setCommont(Commont commont) {
        this.commont = commont;
    }

    public List<Commont> getCommontList() {
        return commontList;
    }

    public void setCommontList(List<Commont> commontList) {
        this.commontList = commontList;
    }
}
