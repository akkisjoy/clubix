package com.prolificwebworks.theclubix.entities;

/**
 * Created by vaibhav on 9/10/15.
 */
public class SnapTW {
    private String doTW;

    private String isAutoImg;

    private String pgID;

    private String imgToUse;

    private String pDate;

    private String isPosted;

    private String isPrePosted;

    private String SNAPformat;

    private String attchImg;

    public String getDoTW() {
        return doTW;
    }

    public void setDoTW(String doTW) {
        this.doTW = doTW;
    }

    public String getIsAutoImg() {
        return isAutoImg;
    }

    public void setIsAutoImg(String isAutoImg) {
        this.isAutoImg = isAutoImg;
    }

    public String getPgID() {
        return pgID;
    }

    public void setPgID(String pgID) {
        this.pgID = pgID;
    }

    public String getImgToUse() {
        return imgToUse;
    }

    public void setImgToUse(String imgToUse) {
        this.imgToUse = imgToUse;
    }

    public String getPDate() {
        return pDate;
    }

    public void setPDate(String pDate) {
        this.pDate = pDate;
    }

    public String getIsPosted() {
        return isPosted;
    }

    public void setIsPosted(String isPosted) {
        this.isPosted = isPosted;
    }

    public String getIsPrePosted() {
        return isPrePosted;
    }

    public void setIsPrePosted(String isPrePosted) {
        this.isPrePosted = isPrePosted;
    }

    public String getSNAPformat() {
        return SNAPformat;
    }

    public void setSNAPformat(String SNAPformat) {
        this.SNAPformat = SNAPformat;
    }

    public String getAttchImg() {
        return attchImg;
    }

    public void setAttchImg(String attchImg) {
        this.attchImg = attchImg;
    }

    @Override
    public String toString() {
        return "ClassPojo [doTW = " + doTW + ", isAutoImg = " + isAutoImg + ", pgID = " + pgID + ", imgToUse = " + imgToUse + ", pDate = " + pDate + ", isPosted = " + isPosted + ", isPrePosted = " + isPrePosted + ", SNAPformat = " + SNAPformat + ", attchImg = " + attchImg + "]";
    }
}

