package com.omotyliu.videoProvider;


import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

public class VideoFile  extends FileSystemResource{

    private long length;

    public VideoFile(File file) {
        super(file);
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public long contentLength() throws IOException {
        return this.length;
    }
}
