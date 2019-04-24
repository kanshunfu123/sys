package com.xiaowei.web.controller.excel;

import com.xiaowei.common.util.excel.ReadExcelFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MOMO on 2019/3/18.
 */
@RestController
@Slf4j
@RequestMapping(value = "/platform/excel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
public class FileUploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request,
                         @RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String name = file.getName();
        ReadExcelFile.readExcelFile(inputStream, originalFilename);
        return "excel上传成功";
    }
    public static void main(String[] args) throws FileNotFoundException {
        URL url = null;

        try {
            url = new URL("https://blog.csdn.net/Horizonhui/article/details/80530737");   //想要读取的url地址
            URLConnection conn = url.openConnection();          //打开url连接
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
