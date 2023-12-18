package com.example.sinhnhat.service;

import java.util.List;

import com.example.sinhnhat.entity.Tag;
import com.example.sinhnhat.model.request.CreateTagRequest;

public interface TagService {
    
    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
