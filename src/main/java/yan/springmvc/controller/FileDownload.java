package yan.springmvc.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileDownload {
    @RequestMapping("filelist")
    public ModelAndView download(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<String> filePath = new ArrayList<>();
        List<String> fileName = new ArrayList<>();
        File file = null;
        try {
            File root = new File(ResourceUtils.getURL("classpath:").getPath());
            String root1 = root.getAbsolutePath();

            String real = root1.substring(0,root1.indexOf(File.separator,3)+1);
            file = new File(real,"static"+File.separator+"upload");
            File[] listFiles = file.listFiles();
            System.out.println("读取下载目录："+file.getAbsolutePath());
                if(!file.isFile()){
                    for(File files:listFiles){
                        fileName.add(files.getName());
                        filePath.add(file+File.separator+files.getName());
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("请检查目录是否存在");
        } catch (NullPointerException e){
            System.out.println("请检查目录是否存在："+file+"\n 目录不存在时先上传文件！");
            modelAndView.setViewName("upload");
            return modelAndView;
        }
        modelAndView.addObject("filePath",filePath);
        modelAndView.addObject("fileName",fileName);
        modelAndView.setViewName("download");
        return modelAndView;
    }

}
