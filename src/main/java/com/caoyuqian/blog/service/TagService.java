package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Atag;
import com.caoyuqian.blog.pojo.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TagService {

    int saveTags(List<Tag> tags);
    int saveTag(Tag tag);
    int saveAtag(Atag tag);
    int getCountByName(String tagName);
    Tag getTagByName(String tagName);
    List<Tag> getTags();
    PageInfo<Atag> getAtags(int pageNo,int pageSize);
    int getCount();
    Tag getTagById(long tagId);
    int updateTag(Atag tag);
    int deleteTag(long tagId);
    int recoveryTag(long tagId);
}
