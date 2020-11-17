package com.example.bt.controller;

import com.example.bt.annotation.WebLog;
import com.example.bt.common.ReturnMsgEnum;
import com.example.bt.dto.UserRole;
import com.example.bt.dto.UserUpadteRole;
import com.example.bt.entity.User;
import com.example.bt.service.Impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peko
 */
@Controller
public class MyController implements WebMvcConfigurer{

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Resource
    UserServiceImpl userService;


    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedOrigins("*");
    }

    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletResponse response){
        return "uploadFile";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String function0(){
        return "redirect:/table";
    }

    @RequestMapping(value = "/table")
    public String table(HttpServletResponse response){
        return "UserTablePlus1";
    }


    @CrossOrigin
    @RequestMapping("/table/all")
    @ResponseBody
    public List<UserRole> tableFindAll(){
        List<UserRole> users = userService.tableFindAll();
        System.out.println(users);
        return users;
    }

    @RequestMapping(value = "/table/editA")
    @ResponseBody
    public String tableEdit1(@RequestBody Map<String, String> params){
        String id = params.get("id");
        String name = params.get("name");
        String phone = params.get("phone");
        System.out.println(id + " " +name+ " " +phone);
        return "SUCCESS";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/table/edit")
    public String tableEdit(@RequestBody UserUpadteRole uur){
        User user = new User();
        BeanUtils.copyProperties(uur,user);
        System.out.println(user.toString());
        System.out.println(uur.toString());
        userService.tableEdit(user);
        userService.tableEditRole(uur);
        return "SUCCESS";
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/table/delete")
    public String tableDelete(@RequestBody User user){
        userService.tableDelete(user);
        userService.tableDeleteRole(user.getUserId());
        return "SUCCESS";
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/table/add")
    public Map<String, String> tableAdd(@RequestBody User user){
        Map<String, String> ret = new HashMap<String, String>();
        System.out.println("controller:"+ user.getUserId());
        User userCO = userService.tableFindByUserId(user.getUserId());
        if(userCO != null){
            ret.put("returnCode", ReturnMsgEnum.FAIL_DATA_EXIST.getValue());
            ret.put("returnMsg", ReturnMsgEnum.FAIL_DATA_EXIST.getName());
            System.out.println("userCO is null");
            return ret;
        }
        userService.tableAdd(user);
        //每个添加的用户身份都有 user 身份
        UserUpadteRole uur = new UserUpadteRole();
        BeanUtils.copyProperties(user,uur);
        String[] roles = {"user"};
        uur.setRoleNames(roles);
        userService.tableEditRole(uur);
        ret.put("returnCode", ReturnMsgEnum.ADD_DATA_SUCCESS.getValue());
        ret.put("returnMsg", ReturnMsgEnum.ADD_DATA_SUCCESS.getName());
        return ret;
    }

    @WebLog
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/table/query")
    public List<UserRole> tableQuery(@RequestBody User user){
//        System.out.println("/table/query:"+myTableDTO.getStatus());
        List<UserRole> list =  userService.tableQuery(user);
        return list;
    }


    /**
     * 图片上传
     * @param photo
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request,HttpServletResponse response) {
        Map<String, String> ret = new HashMap<String, String>();
//        if (photo == null) {
//            ret.put("type", "error");
//            ret.put("msg", "选择要上传的文件！");
//            return ret;
//        }
//        if (photo.getSize() > 1024 * 1024 * 10) {
//            ret.put("type", "error");
//            ret.put("msg", "文件大小不能超过10M！");
//            return ret;
//        }

        logger.info("getName = "+photo.getName());
        logger.info("getOriginalFilename = "+photo.getOriginalFilename());
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,bmp,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,bmp,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        //String savePath = getUrl()+"/img/";
        //存在指定路径
        String savePath = "E:/imagesFile/";

        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            System.out.println("若不存在该目录，则创建目录");
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            System.out.println("保存文件异常！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", savePath);
        ret.put("filename", filename);

        System.out.println("保存文件成功："+filename);

        return ret;
    }

    public static  String  getUrl() {

        String path = null;
        try {
            String serverPath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ");
            //从路径字符串中取出工程路径
            path=serverPath.substring(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }

    @ResponseBody
    @RequestMapping(value = "/downloadPhoto/{fileN}")
    public String downloadPhoto(@PathVariable String fileN,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName = fileN;
        if(fileName!=null){
            String filePath = "E:/imagesFile/" + fileName;
            System.out.println(filePath);
            File file = new File(filePath);
            if(file.exists()){
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try{
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while(i!=-1){
                        System.out.println("读到内容了："+i);
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                    return "download success";
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    bis.close();
                    fis.close();
                }
            }
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFile")
    public String downloadFile01(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String fileName = "drrr.jpg";
        if(fileName!=null){
            String filePath = "E:/imagesFile/" + fileName;
            File file = new File(filePath);
            if(file.exists()){
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try{
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while(i!=-1){
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                    return "download success";
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    bis.close();
                    fis.close();
                }
            }
        }
        return "failure";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadFile(MultipartFile photo, HttpServletRequest request,HttpServletResponse response) {
        Map<String, String> ret = new HashMap<String, String>();
//        if (photo == null) {
//            ret.put("type", "error");
//            ret.put("msg", "选择要上传的文件！");
//            return ret;
//        }
//        if (photo.getSize() > 1024 * 1024 * 10) {
//            ret.put("type", "error");
//            ret.put("msg", "文件大小不能超过10M！");
//            return ret;
//        }



        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"xlsx,xls,doc,docx".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择xlsx,xls,doc,docx格式的文件！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        String savePath = "E:/imagesFile/";

        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            System.out.println("若不存在该目录，则创建目录");
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            System.out.println("保存文件异常！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传文件成功！");
        ret.put("filepath", savePath);
        ret.put("filename", filename);

        System.out.println("保存文件成功："+filename);

        return ret;
    }

}

