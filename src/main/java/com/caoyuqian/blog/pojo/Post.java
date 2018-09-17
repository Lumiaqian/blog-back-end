package com.caoyuqian.blog.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
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
@Document(indexName = "posts_index",type = "post")
@Mapping(mappingPath = "post_mapping.json")
public class Post implements Serializable {

    @Id
    private String postId;//ID
    private String title;//文章标题
    private String content;//文章内容
    private Date publicDate;//发表时间
    private Date editDate;//修改时间
    private String path;//保存路径
    private List<Tag> tags;//标签
    private List<Category> categories;//类别
    private int watchCount;//阅读次数

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
                ", path='" + path + '\'' +
                ", tags=" + tags +
                ", categories=" + categories +
                ", watchCount=" + watchCount +
                '}';
    }
}
