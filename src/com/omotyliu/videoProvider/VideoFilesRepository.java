package com.omotyliu.videoProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class VideoFilesRepository implements FileRepository{


    @Override
    public void saveFile(File file) {

    }

    @Override
    public FileSystemResource getFile(String fileName) {
        File file = new File("/nfs/2016/o/omotyliu/Downloads/oceans1.mp4");
        VideoFile video = new VideoFile(file);
        video.setLength(new File("/nfs/2016/o/omotyliu/Downloads/oceans.mp4").length());
        return video;
    }
}
