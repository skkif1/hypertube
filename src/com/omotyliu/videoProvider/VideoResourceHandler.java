package com.omotyliu.videoProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;

@Component
public class VideoResourceHandler extends ResourceHttpRequestHandler{


    private final FileRepository videoRepo;

    @Autowired
    public VideoResourceHandler(FileRepository videoRepo) {
        super();
        this.videoRepo = videoRepo;
        System.out.println("video resource ");
    }


    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {

        FileSystemResource video = videoRepo.getFile("sad");
        return video;
    }


}
