package com.omotyliu;

import com.omotyliu.videoProvider.VideoResourceHandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    static int inc = 0;

    private VideoResourceHandler videoHandler;

    public HelloController(VideoResourceHandler videoHandler) {
        this.videoHandler = videoHandler;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getView() {
        return "hello";
    }


    @RequestMapping("/video")
    public void getVideo(HttpServletRequest request, HttpServletResponse respone) throws FileNotFoundException {


        if(!new File("/nfs/2016/o/omotyliu/Downloads/film1.mp4").exists())
        {
            download();
        }

        try {
            videoHandler.handleRequest(request, respone);
        } catch (ServletException | IOException e) {
        }
    }


    private static void download() throws FileNotFoundException{

        final File src = new File("/nfs/2016/o/omotyliu/Downloads/film.mp4");
        final File dest = new File("/nfs/2016/o/omotyliu/Downloads/film1.mp4");

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest));

        new Thread(() ->
        {
            try {
                while (inputStream.available() > 0)
                    outputStream.write(inputStream.read());
            } catch (IOException e) {
                System.out.println("read exception");
                e.printStackTrace();
            }

        }).start();

        while (!dest.canRead() || dest.length() < 1024 * 1000 * 1)
            ;

    }

}
