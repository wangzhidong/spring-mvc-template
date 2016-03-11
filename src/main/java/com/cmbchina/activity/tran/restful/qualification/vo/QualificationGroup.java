package com.cmbchina.activity.tran.restful.qualification.vo;

import java.util.List;

/**
 * Created by wangtingbang on 16-2-17.
 */
public class QualificationGroup{
    private String quaGroupId;
    private String quaGroupName;
    private String description;

    private List<Qualification> qualifications;

    public QualificationGroup() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuaGroupId() {
        return quaGroupId;
    }

    public void setQuaGroupId(String quaGroupId) {
        this.quaGroupId = quaGroupId;
    }

    public String getQuaGroupName() {
        return quaGroupName;
    }

    public void setQuaGroupName(String quaGroupName) {
        this.quaGroupName = quaGroupName;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
