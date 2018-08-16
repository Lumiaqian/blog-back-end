package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.TagMapper;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qian
 * @version V1.0
 * @Title: TagServiceImpl
 * @Package: com.caoyuqian.blog.service.impl
 * @Description: TOTO
 * @date 2018/8/15 上午10:38
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int saveTags(List<Tag> tags) {
        return tagMapper.saveTags(tags);
    }

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public int getCountByName(String tagName) {
        return tagMapper.getCountByName(tagName);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagMapper.getTagByName(tagName);
    }
}
