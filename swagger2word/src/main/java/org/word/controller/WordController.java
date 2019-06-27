package org.word.controller;

import com.sun.xml.internal.txw2.Document;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;
import org.word.dto.Table;
import org.word.service.WordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yinjie
 */
@Controller
public class WordController {

    @Autowired
    private WordService tableService;

    /**
     * @param model
     * @return
     * @see #toWord(Model)
     */
    @Deprecated
    @RequestMapping("/getWord")
    public String getWord(Model model) {
        List<Table> tables = tableService.tableList();
        model.addAttribute("tables", tables);
        return "word";
    }


    @RequestMapping("/toWord")
    public String toWord(Model model) {
        List<Table> tables = tableService.tableList();
        model.addAttribute("tables", tables);
        return "word";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String toWord1(HttpServletRequest request, HttpServletResponse response) {
        RestTemplate template=new RestTemplate();
        //请求接口获取页面
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        headers.add(
                "Accept",
                "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,*/*;q=0.8");
        headers.add("Accept-Encoding", "gzip, deflate, sdch");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = template.exchange("http://localhost:8080/getWord",
                HttpMethod.GET, requestEntity, String.class);
        String html=responseEntity.getBody();

        return  "ojbk";
    }


}
