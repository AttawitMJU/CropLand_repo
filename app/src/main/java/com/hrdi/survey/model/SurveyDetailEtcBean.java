package com.hrdi.survey.model;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyDetailEtcBean {


    // Attribute
    private String etc_id;
    private String survey_id;
    private String land_No;
    private String card_no;
    private String detail;
    private String update_Date;
    private String update_By;

    public SurveyDetailEtcBean() {

    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SurveyDetailEtcBean other = (SurveyDetailEtcBean) obj;
        if (etc_id != other.etc_id)
            return false;
        return true;
    }


    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getLand_No() {
        return land_No;
    }

    public void setLand_No(String land_No) {
        this.land_No = land_No;
    }


    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getEtc_id() {
        return etc_id;
    }

    public void setEtc_id(String etc_id) {
        this.etc_id = etc_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public String getUpdate_By() {
        if (update_By == null || "".equals(update_By))
            update_By = "0";

        return update_By;
    }

    public void setUpdate_By(String update_By) {
        this.update_By = update_By;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SurveyEtcBean{");
        sb.append("etc_id='").append(etc_id).append('\'');
        sb.append(", survey_id='").append(survey_id).append('\'');
        sb.append(", land_No='").append(land_No).append('\'');
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", update_Date='").append(update_Date).append('\'');
        sb.append(", update_By='").append(update_By).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
