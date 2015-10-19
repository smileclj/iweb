package com.clj.panda.controller.test;

import com.clj.panda.common.enums.PandaCode;
import com.clj.panda.common.exceptions.PandaException;
import com.clj.panda.common.resp.Result;
import com.clj.panda.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lao on 2015/9/28.
 */
@RestController
@RequestMapping(value="test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value="/testView.htm")
    public ModelAndView testView(@RequestParam("id") String id){
        if(id == null || id.equals("")){
            throw new PandaException(PandaCode.ERROR_PARAM);
        }
        ModelAndView view = new ModelAndView("page/test/TestJsp");
        view.addObject("user","panda");
        return view;
    }

    /**
     * 返回json数据
     * @return
     */
    @RequestMapping(value="/testJson.htm")
    public Result testJson(@RequestParam("id") String id){
        if(id == null || id.equals("")){
            throw new PandaException(PandaCode.ERROR_PARAM);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user", "clj");
        map.put("id",id);
//        map.put("date",sdf.format(date));
        return new Result(PandaCode.SUCCESS,map);
    }

    @RequestMapping(value="/singleUpload.htm")
    public Result singleUpload(MultipartFile file,String comment,HttpServletRequest request,HttpServletResponse response){
        logger.debug("单文件上传");
        logger.debug("comment:"+comment);
        logger.debug(file.getOriginalFilename());
        return new Result(PandaCode.SUCCESS);
    }

    @RequestMapping(value="/multiUpload.htm")
    public Result multiUpload(@RequestParam(value="file")CommonsMultipartFile[] files,String comment,HttpServletRequest request,HttpServletResponse response){
        logger.debug("多文件上传");
        logger.debug("comment:"+comment);
        for(int i = 0;i<files.length;i++){
            logger.debug(files[i].getOriginalFilename());
        }
        return new Result(PandaCode.SUCCESS);
    }

    @RequestMapping(value="/downloadOne.htm")
    public void downloadOne(HttpServletRequest request,HttpServletResponse response){
        String fileSource = request.getServletContext().getRealPath("/") + "file" + File.separator + "test.txt";
        logger.info("file path == " + fileSource);
//        response.setContentType(request.getServletContext().getMimeType(fileSource.substring(fileSource.lastIndexOf("\\")+1)));
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=test.txt");
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(new File(fileSource)));
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[1024];
            int b = 0;
            while((b=in.read(buff)) != -1){ //b是读取的总字节数
                out.write(buff,0,b);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            logger.error("文件不存在" + e);
            throw new PandaException(PandaCode.FILE_NOT_EXIST);
        } catch (IOException e){
            throw new PandaException(PandaCode.UNKNOW);
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 打印输出方法
     * 容器会帮忙关闭
     * @param response
     * @param data
     */
    public void print(HttpServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
