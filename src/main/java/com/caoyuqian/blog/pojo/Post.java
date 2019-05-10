package com.caoyuqian.blog.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: Post
 * @Package: com.caoyuqian.blog.pojo
 * @Description: Post文章类
 * @date 2018/8/14 下午2:24
 **/
@Document(indexName = "posts_index", type = "post")
// @Mapping(mappingPath = "post_mapping.json")
public class Post implements Serializable {

    //ID
    @Id
    private String postId;
    //文章标题
    @Field(searchAnalyzer = "ik_smart",analyzer = "ik_max_word",type = FieldType.Text)
    private String title;
    //文章内容
    @Field(searchAnalyzer = "ik_smart",analyzer = "ik_max_word",type = FieldType.Text)
    private String content;
    //发表时间
    private Date publicDate;
    //修改时间
    private Date editDate;
    //保存时间
    private Date saveDate;
    //保存路径
    private String path;
    // @Field(searchAnalyzer = "ik_smart",analyzer = "ik_smart")
    //标签
    private List<Tag> tags;
    // @Field(searchAnalyzer = "ik_smart",analyzer = "ik_smart")
    //类别
    private List<Category> categories;
    //阅读次数
    private int watchCount;
    //1表示已发布状态、0表示未发布状态、-1表示已经删除在垃圾箱中的状态 默认为0
    private int status;
    //0表示关闭评论，1表示开启评论
    private boolean openComment;

    public boolean isOpenComment() {
        return openComment;
    }

    public void setOpenComment(boolean openComment) {
        this.openComment = openComment;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicDate=" + publicDate +
                ", editDate=" + editDate +
                ", saveDate=" + saveDate +
                ", path='" + path + '\'' +
                ", tags=" + tags +
                ", categories=" + categories +
                ", watchCount=" + watchCount +
                ", status=" + status +
                ", openComment=" + openComment +
                '}';
    }
}
