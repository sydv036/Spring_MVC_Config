package com.example.JSFW_L_A103.controller;

import com.example.JSFW_L_A103.common.BaseGetSession;
import com.example.JSFW_L_A103.constant.BasePaging;
import com.example.JSFW_L_A103.constant.PageBaseDefault;
import com.example.JSFW_L_A103.dtos.reponse.ContentReponse;
import com.example.JSFW_L_A103.dtos.request.ContentAddRequest;
import com.example.JSFW_L_A103.dtos.request.ContentUpdateRequest;
import com.example.JSFW_L_A103.entity.Member;
import com.example.JSFW_L_A103.service.IContentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/member/content")
public class ContentController extends BaseGetSession {

    private static String messageContent = "";

    @Autowired
    private IContentService contentService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public String getContent(Model model, @RequestParam("page") Optional<Integer> pageNumber) {
        BasePaging<ContentReponse> pageBasePaging = contentService.getAllContentPaging(pageNumber.orElse(PageBaseDefault.PAGE_NUMBER_DEFAULT) - 1, PageBaseDefault.PAGE_SIZE_DEFAULT);
        model.addAttribute("content", pageBasePaging);
        model.addAttribute("object", new ContentUpdateRequest());
        return "view-content";
    }

    @GetMapping("/search")
    public String searchContentTitle(Model model, @RequestParam("title") String title, @RequestParam("page") Optional<Integer> pageNumber) {
        BasePaging<ContentReponse> pageBasePaging = contentService.getContentPagingByTitle(title, pageNumber.orElse(PageBaseDefault.PAGE_NUMBER_DEFAULT) - 1, PageBaseDefault.PAGE_SIZE_DEFAULT);
        model.addAttribute("content", pageBasePaging);
        model.addAttribute("object", new ContentUpdateRequest());
        return "view-content";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> detailContent(@PathVariable("id") String id) throws JsonProcessingException {
        try {
            ContentReponse contentReponse = contentService.findByIds(id);
            if (contentReponse != null) {
                return ResponseEntity.status(200).body(objectMapper.writeValueAsString(contentReponse));
            }
            return ResponseEntity.status(404).body("Not Found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server Error");
        }


    }

    @GetMapping("/add")
    public String getFormContent(Model model) {
        model.addAttribute("content", new ContentAddRequest());
        return "form-content";
    }

    @PostMapping("/add")
    public String createFormContent(@ModelAttribute("content") @Valid ContentAddRequest contentAddRequest,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", contentAddRequest);
            return "form-content";
        }
        Member member = (Member) model.getAttribute("memberSession");
        contentAddRequest.setMember(member);
        String result = contentService.saveContent(contentAddRequest);
//        redirectAttributes.addAttribute("message", result);
        return "redirect:/member/content";
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateContent(@Valid @RequestBody ContentUpdateRequest contentUpdateRequest,
                                           BindingResult bindingResult, Model model,
                                           RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("content", contentUpdateRequest);
                return ResponseEntity.status(403).body(bindingResult.getAllErrors());
            }
            String result = contentService.updateContent(contentUpdateRequest);
            return ResponseEntity.status(200).body(objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteFormContent(@PathVariable("id") String id, Model model) {
        Boolean isCheck = contentService.deleteContent(id, model);
        if (isCheck) {
            return ResponseEntity.status(HttpStatus.OK).body("/member/content");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error");
    }
}
