package com.omotyliu.videoProvider;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

public interface FileRepository {

    public void saveFile(File file);
    public FileSystemResource getFile(String fileName);
}
