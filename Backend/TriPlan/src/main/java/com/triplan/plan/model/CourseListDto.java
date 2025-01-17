package com.triplan.plan.model;

public class CourseListDto {
    private int planId;
    private int ContentId;
    private int order;
    private String comment;
    private String planComment;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getContentId() {
        return ContentId;
    }

    public void setContentId(int contentId) {
        ContentId = contentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getPlanComment() {
		return planComment;
	}

	public void setPlanComment(String planComment) {
		this.planComment = planComment;
	}

	@Override
	public String toString() {
		return "CourseListDto [planId=" + planId + ", ContentId=" + ContentId + ", order=" + order + ", comment="
				+ comment + ", planComment=" + planComment + "]";
	}

}
