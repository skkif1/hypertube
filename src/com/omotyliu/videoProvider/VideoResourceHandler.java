package com.omotyliu.videoProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

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

//        FileSystemResource video = new FileSystemResource("/nfs/2016/o/omotyliu/Downloads/film.mp4");

         FileSystemResource video = videoRepo.getFile("sad");

         return video;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.handleRequest(request, response);

    }
}
