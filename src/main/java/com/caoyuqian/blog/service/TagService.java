package com.caoyuqian.blog.service;

import com.caoyuqian.blog.pojo.Tag;

import java.util.List;

public interface TagService {

    int saveTags(List<Tag> tags);
    int saveTag(Tag tag);
    int getCountByName(String tagName);
    Tag getTagByName(String tagName);
    List<Tag> getTags();
    int getCount();
}
