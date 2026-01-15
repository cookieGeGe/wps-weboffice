package io.github.wpsDemo.controllers;

import io.github.cookiegege.wpsOffice.api.WpsDocumentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * @author JoSuper
 * @date 2026/1/14 9:57
 */
@RestController
public class TestController {

    @Resource
    private WpsDocumentService wpsDocumentService;


    @GetMapping("/test/preview")
    public String testPreview() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hello", "1");
        map.put("t", 1);
        map.put("show", true);
        String previewUrl = wpsDocumentService.getPreviewUrl(
                "demo", "123.docx", "123", "123", map
        );
        System.out.println(previewUrl);
        return "ok";
    }

    @GetMapping("/test/edit")
    private String testEdit() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hello", "1");
        map.put("t", 1);
        map.put("show", true);
        String previewUrl = wpsDocumentService.getEditUrl(
                "demo", "123.docx", "123", "123", map
        );
        System.out.println(previewUrl);
        return "ok";
    }


}
