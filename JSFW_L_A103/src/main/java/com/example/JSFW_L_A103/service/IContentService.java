package com.example.JSFW_L_A103.service;

import com.example.JSFW_L_A103.constant.BasePaging;
import com.example.JSFW_L_A103.dtos.reponse.ContentReponse;
import com.example.JSFW_L_A103.dtos.request.ContentAddRequest;
import com.example.JSFW_L_A103.dtos.request.ContentUpdateRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

public interface IContentService {
    BasePaging<ContentReponse> getAllContentPaging(int pageNumber, int pageSize);

    BasePaging<ContentReponse> getContentPagingByTitle(String title, int pageNumber, int pageSize);

    String saveContent(ContentAddRequest contentAddRequest);

    String updateContent(ContentUpdateRequest contentUpdateRequest);

    Boolean deleteContent(String id, Model model);

    ContentReponse findByIds(String id);
}
