package yan.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUpload {

    @RequestMapping("/upload")
    public ModelAndView upload(@RequestParam("imgs") MultipartFile[] img, HttpServletRequest request) {
//        String realPath = request.getSession().getServletContext().getRealPath("file")+File.separator;
        ModelAndView modelAndView = new ModelAndView();
        List filePath = new ArrayList();
        File file = null;
        try {
            File root = new File(ResourceUtils.getURL("classpath:").getPath());
            String root1 = root.getAbsolutePath();

            String real = root1.substring(0,root1.indexOf(File.separator,3)+1);
            file = new File(real,"static"+File.separator+"upload");

            if (!file.exists()) {
                file.mkdirs();
                System.out.println("创建的上传路径"+file);
            }

            for (MultipartFile imgs : img) {
                if (imgs.getSize() > 0) {
                    String fileName = imgs.getOriginalFilename();
                    File realFile = new File(file, fileName);
                    if (!realFile.exists()) {
                        if (!realFile.getParentFile().exists()) {
                            realFile.getParentFile().mkdirs();
                        }
                        filePath.add(realFile.getPath());
                        modelAndView.addObject("filePath", filePath);
                        imgs.transferTo(realFile);
                        System.out.println("文件上传的路径"+realFile.getAbsolutePath());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("upload");
        return modelAndView;
    }
}
