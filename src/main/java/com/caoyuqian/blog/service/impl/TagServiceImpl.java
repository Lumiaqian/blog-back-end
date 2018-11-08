package com.caoyuqian.blog.service.impl;

import com.caoyuqian.blog.mapper.TagMapper;
import com.caoyuqian.blog.pojo.Atag;
import com.caoyuqian.blog.pojo.Tag;
import com.caoyuqian.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public int saveAtag(Atag tag) {
        return tagMapper.saveAtag(tag);
    }

    @Override
    public int getCountByName(String tagName) {
        return tagMapper.getCountByName(tagName);
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagMapper.getTagByName(tagName);
    }

    @Override
    public List<Tag> getTags() {
        return tagMapper.getTags();
    }

    @Override
    public PageInfo<Atag> getAtags(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Atag> atags=tagMapper.getAtags();
        PageInfo<Atag> tags=new PageInfo<>(atags);
        return tags;
    }

    @Override
    public int getCount() {
        return tagMapper.getCount();
    }

    @Override
    public Tag getTagById(long tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public int updateTag(Atag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(long tagId) {
        return tagMapper.deleteTag(tagId);
    }

    @Override
    public int recoveryTag(long tagId) {
        return tagMapper.recoveryTag(tagId);
    }
}
