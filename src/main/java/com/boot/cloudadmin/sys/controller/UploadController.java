package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.config.DeployUtil;
import com.boot.cloudadmin.common.contants.GlobalContants;
import com.boot.cloudadmin.sys.entity.AttachsEntity;
import com.boot.cloudadmin.sys.service.IAttachsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private DeployUtil deployUtil;

    private final ResourceLoader resourceLoader;

    @Autowired
    private IAttachsService attachsService;

    @Autowired
    public UploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping("/upload")
    public R uploadPic(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info(file.getOriginalFilename()+ "=======" +file.getContentType() + file.getName() + "=====" + file.getSize() + "params:--> " + params.toString());
        if (file.isEmpty()) {
            return R.error(GlobalContants.PAEAMS_ERROR_CODE,"上传文件不能为空");
        }
        try {
            /** 获取配置的本地路径 **/
            String rootPathDir = deployUtil.getImgPath();
            /** 构建按照日期存储的本地路径**/
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fullPathDir = rootPathDir + File.separator + dateFormat.format(new Date());
            /** 根据本地路径创建目录**/
            File fullPathFile = new File(fullPathDir);
            if (!fullPathFile.exists()){
                fullPathFile.mkdirs();
            }
            /** 获取文件的后缀* */
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            /** 使用UUID生成文件名称**/
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + suffix;
            /** 拼成完整的文件保存路径加文件**/
            String filePath = fullPathFile + File.separator + fileName;
            /** 文件输出流* */
            File targetFile = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
            BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);
            stream.write(file.getBytes());
            /** 打印出上传到服务器的文件的本地路径和网络路径**/
            /** 入附件库 **/
            AttachsEntity attachsEntity = new AttachsEntity();
            attachsEntity.setName(file.getOriginalFilename());
            attachsEntity.setFileSize(file.getSize());
            attachsEntity.setFilePath("/images/" + dateFormat.format(new Date()) + "/" + fileName);
            attachsEntity.setSuffix(suffix);
            attachsEntity.setType(file.getContentType());
            attachsEntity.setAttachType(Integer.parseInt(params.get("attach_type").toString()));
            attachsService.insert(attachsEntity);
            return R.ok().put("path","/images/" + dateFormat.format(new Date()) + "/" + fileName).put("fileName",fileName).put("uuid",uuid).put("attachId",attachsEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
            R.error(GlobalContants.UPLOAD_ERROR_CODE,"文件上传错误");
        }
        return R.ok();
    }

    /**
     * 富文本图片上传接口
     * @param file
     * @return
     */
    @RequestMapping("/editUpload")
    public R editUpload(@RequestParam("file") MultipartFile file){
        logger.info(file.getOriginalFilename()+ "=======" +file.getContentType() + file.getName() + "=====" + file.getSize());
        if (file.isEmpty()) {
            return R.error(GlobalContants.PAEAMS_ERROR_CODE,"上传文件不能为空");
        }
        try {
            /** 获取配置的本地路径 **/
            String rootPathDir = deployUtil.getImgPath();
            /** 构建按照日期存储的本地路径**/
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fullPathDir = rootPathDir + File.separator + dateFormat.format(new Date());
            /** 根据本地路径创建目录**/
            File fullPathFile = new File(fullPathDir);
            if (!fullPathFile.exists()){
                fullPathFile.mkdirs();
            }
            /** 获取文件的后缀* */
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            /** 使用UUID生成文件名称**/
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + suffix;
            /** 拼成完整的文件保存路径加文件**/
            String filePath = fullPathFile + File.separator + fileName;
            /** 文件输出流* */
            File targetFile = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
            BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);
            stream.write(file.getBytes());
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("src","/images/" + dateFormat.format(new Date()) + "/" + fileName);
            data.put("title",fileName);
            return R.ok().put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(GlobalContants.UPLOAD_ERROR_CODE,"文件上传错误");
        }
    }

}
