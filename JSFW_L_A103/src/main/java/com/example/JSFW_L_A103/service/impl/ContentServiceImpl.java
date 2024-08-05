package com.example.JSFW_L_A103.service.impl;

import com.example.JSFW_L_A103.common.ValidationCommon;
import com.example.JSFW_L_A103.constant.BasePaging;
import com.example.JSFW_L_A103.dtos.reponse.ContentReponse;
import com.example.JSFW_L_A103.dtos.request.ContentAddRequest;
import com.example.JSFW_L_A103.dtos.request.ContentUpdateRequest;
import com.example.JSFW_L_A103.entity.Content;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.repository.IContentRepository;
import com.example.JSFW_L_A103.service.IContentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private IContentRepository contentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BasePaging<ContentReponse> getAllContentPaging(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ContentReponse> contentReponses = contentRepository.findAllContent(pageable);
        return new BasePaging<>(contentReponses);
    }

    @Override
    public BasePaging<ContentReponse> getContentPagingByTitle(String title, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ContentReponse> contentReponses = contentRepository.findContentByTitle("%" + title + "%", pageable);
        return new BasePaging<>(contentReponses);
    }

    @Override
    public String saveContent(ContentAddRequest contentAddRequest) {
        try {
            Content contentCreate = null;
            if (contentAddRequest != null) {
                Content content = mapper.map(contentAddRequest, Content.class);
                contentCreate = contentRepository.saveAndFlush(content);
            }
            return ValidationCommon.isCheckProcess(contentCreate);

        } catch (Exception e) {
            e.printStackTrace();
            return ValidationCommon.isCheckProcess(null);
        }
    }

    @Override
    public String updateContent(ContentUpdateRequest contentUpdateRequest) {
        try {
            Content contentUpdate = null;
            Content contentCurrent = contentRepository.findById(contentUpdateRequest.getId()).get();
            if (contentUpdateRequest != null) {
                contentCurrent.setContent(contentUpdateRequest.getContent());
                contentCurrent.setBrief(contentUpdateRequest.getBrief());
                contentCurrent.setUpdateTime(contentUpdateRequest.getUpdateTime());
                contentCurrent.setTitle(contentUpdateRequest.getTitle());
                contentUpdate = contentRepository.saveAndFlush(contentCurrent);
            }
            return ValidationCommon.isCheckProcess(contentUpdate);

        } catch (Exception e) {
            e.printStackTrace();
            return ValidationCommon.isCheckProcess(null);
        }
    }

    @Override
    public Boolean deleteContent(String id, Model model) {
        Member member = (Member) model.getAttribute("memberSession");
        Optional<Content> content = contentRepository.findById(id);
        if (member.getId() == content.get().getMember().getId()) {
            contentRepository.delete(content.get());
            return true;
        }
        return false;
    }

    @Override
    public ContentReponse findByIds(String id) {
        try {
            return contentRepository.findByIds(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
