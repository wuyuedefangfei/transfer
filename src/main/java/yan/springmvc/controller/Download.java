package yan.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class Download {
    @RequestMapping("/download")
    public void download(@RequestParam("lujing") String lujing, @RequestParam("downloadfileName") String downloadfileName, HttpServletRequest request, HttpServletResponse response){
//        String downloadPath = request.getSession().getServletContext().getRealPath("downloadFile");
        File uploadFile = new File(lujing);
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + URLEncoder.encode(downloadfileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(!lujing.isEmpty()){
            try {
                InputStream inputStream = new FileInputStream(uploadFile);
                OutputStream outputStream = response.getOutputStream();
                byte[] bytes = new byte[30720];
                int temp = 0;
                while ((temp=inputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,temp);
                }
                outputStream.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
