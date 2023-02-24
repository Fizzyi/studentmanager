package com.study.sutdentmanager.pojo;

/**
 * 请假实体类
 */
public class Leave {
    public static int LEAVE_STATUS_WAIT = 0; //等待审核
    public static int LEAVE_STATUS_AGREE = 1;//同意
    public static int LEAVE_STATUS_DISAGREE = -1;//不同意

    private int id;
    private int studentId;
    private String info; //请假理由
    private int status = LEAVE_STATUS_WAIT;
    private String remark;

    public static int getLeaveStatusWait() {
        return LEAVE_STATUS_WAIT;
    }

    public static void setLeaveStatusWait(int leaveStatusWait) {
        LEAVE_STATUS_WAIT = leaveStatusWait;
    }

    public static int getLeaveStatusAgree() {
        return LEAVE_STATUS_AGREE;
    }

    public static void setLeaveStatusAgree(int leaveStatusAgree) {
        LEAVE_STATUS_AGREE = leaveStatusAgree;
    }

    public static int getLeaveStatusDisagree() {
        return LEAVE_STATUS_DISAGREE;
    }

    public static void setLeaveStatusDisagree(int leaveStatusDisagree) {
        LEAVE_STATUS_DISAGREE = leaveStatusDisagree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
