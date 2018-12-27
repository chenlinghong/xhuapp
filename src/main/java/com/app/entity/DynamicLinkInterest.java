package com.app.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 动态和小类型的结合类
 */

@Component(value = "dynamicLinkInterest")
@Scope(value = "prototype")
public class DynamicLinkInterest {
    private int dynamic_id;
    private int look_persons;
    private int interest_type;

    public DynamicLinkInterest() {
    }

    public int getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(int dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public int getLook_persons() {
        return look_persons;
    }

    public void setLook_persons(int look_persons) {
        this.look_persons = look_persons;
    }

    public int getInterest_type() {
        return interest_type;
    }

    public void setInterest_type(int interest_type) {
        this.interest_type = interest_type;
    }
}
