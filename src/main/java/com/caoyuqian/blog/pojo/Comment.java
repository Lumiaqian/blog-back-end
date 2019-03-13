package com.caoyuqian.blog.pojo;

import java.util.Date;

/**
 * @author qian
 * @version V1.0
 * @Title: comment
 * @Package: com.caoyuqian.blog.pojo
 * @Description: 评论实体类
 * @date 2019-01-04 09:39
 **/
public class Comment {
    //评论id
    private long id;
    //评论的文章id
    private String postId;
    //评论者
    private String commentator;
    //评论者邮箱
    private String email;
    //评论的内容
    private String content;
    //评论创建时间
    private Date createDate;
    //评论删除时间
    private Date deleteDate;
    //状态
    private boolean status;
    //是否是作者
    private boolean isAuthor;
    //父id
    private long fatherId;
    //回复id
    private long replyId;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId='" + postId + '\'' +
                ", commentator='" + commentator + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", deleteDate=" + deleteDate +
                ", status=" + status +
                ", isAuthor=" + isAuthor +
                ", fatherId=" + fatherId +
                ", replyId=" + replyId +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(boolean isAuthor) {
        this.isAuthor = isAuthor;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }
}
