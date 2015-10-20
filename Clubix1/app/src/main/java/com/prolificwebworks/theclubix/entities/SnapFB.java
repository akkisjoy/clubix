package com.prolificwebworks.theclubix.entities;

/**
 * Created by vaibhav on 9/10/15.
 */
public class SnapFB {

    private String isAutoImg;

    private String postType;

    private String AttachPost;

    private String isAutoURL;

    private String urlToUse;

    private String pgID;

    private String doFB;

    private String imgToUse;

    private String pDate;

    private String isPosted;

    private String isPrePosted;

    private String SNAPformat;

    public String getIsAutoImg() {
        return isAutoImg;
    }

    public void setIsAutoImg(String isAutoImg) {
        this.isAutoImg = isAutoImg;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getAttachPost() {
        return AttachPost;
    }

    public void setAttachPost(String AttachPost) {
        this.AttachPost = AttachPost;
    }

    public String getIsAutoURL() {
        return isAutoURL;
    }

    public void setIsAutoURL(String isAutoURL) {
        this.isAutoURL = isAutoURL;
    }

    public String getUrlToUse() {
        return urlToUse;
    }

    public void setUrlToUse(String urlToUse) {
        this.urlToUse = urlToUse;
    }

    public String getPgID() {
        return pgID;
    }

    public void setPgID(String pgID) {
        this.pgID = pgID;
    }

    public String getDoFB() {
        return doFB;
    }

    public void setDoFB(String doFB) {
        this.doFB = doFB;
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

    @Override
    public String toString() {
        return "ClassPojo [isAutoImg = " + isAutoImg + ", postType = " + postType + ", AttachPost = " + AttachPost + ", isAutoURL = " + isAutoURL + ", urlToUse = " + urlToUse + ", pgID = " + pgID + ", doFB = " + doFB + ", imgToUse = " + imgToUse + ", pDate = " + pDate + ", isPosted = " + isPosted + ", isPrePosted = " + isPrePosted + ", SNAPformat = " + SNAPformat + "]";
    }
}
