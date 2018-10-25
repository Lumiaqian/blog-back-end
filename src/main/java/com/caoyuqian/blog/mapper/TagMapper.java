package com.caoyuqian.blog.mapper;

import com.caoyuqian.blog.pojo.Tag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    int saveTag(@Param("tag")Tag tag);
    int saveTags(@Param("tags") List<Tag> tagList );
    int getCountByName(@Param("tag_name") String tagName);
    Tag getTagByName(@Param("tagName") String tagName);
    List<Tag> getTags();
    int getCount();
    Tag getTagById(@Param("tag_id") long tagId);
}
