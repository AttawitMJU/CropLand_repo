package com.hrdi.survey.model;

/**
 * Created by attawit on 1/12/15 AD.
 */
public class MetaBean {

    private int itemId;
    private String itemName;
    private String itemRef;
    private String itemValue;

    private String update_by;
    private String update_date;
    private String remark;

    public MetaBean() {

    }

    public MetaBean(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;

    }

    public MetaBean(int itemId, String itemName, String itemRef, String itemValue) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemRef = itemRef;
        this.itemValue = itemValue;
    }

    public MetaBean(int itemId, String itemName, String itemRef, String itemValue, String remark) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemRef = itemRef;
        this.itemValue = itemValue;
        this.remark= remark;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRef() {
        return itemRef;
    }

    public void setItemRef(String itemRef) {
        this.itemRef = itemRef;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaBean metaBean = (MetaBean) o;

        if (itemId != metaBean.itemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return itemId;
    }
}
