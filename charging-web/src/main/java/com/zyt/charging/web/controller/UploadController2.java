//package com.zyt.charging.web.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zyt.charging.web.fastdfs.StorageService;
//import com.zyt.charging.web.utlis.UploadDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class UploadController2 {
//    @Value("${fastdfs.base.url}")
//    private String FASTDFS_BASE_URL;
//
//    @Autowired
//    private StorageService storageService;
//
//    /**
//     * 文件上传
//     *
//     * @param dropFile    Dropzone
//     * @return
//     */
//    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    public String upload(@RequestParam(value = "editormd-image-file", required = false)MultipartFile dropFile) {
//        UploadDTO uploadDTO = new UploadDTO ();
//
//        // Dropzone 上传
//        if (dropFile != null) {
//            uploadDTO.setSuccess (1);
//            uploadDTO.setMessage ("上传图片成功");
//            uploadDTO.setUrl (writeFile(dropFile));
//        }
//
//        try {
//            String result = new ObjectMapper ().writeValueAsString (uploadDTO);
//            return result;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace ();
//        }
//
//
//        return null;
//    }
//
//    /**
//     * 将图片写入指定目录
//     *
//     * @param multipartFile
//     * @return 返回文件完整路径
//     */
//    private String writeFile(MultipartFile multipartFile) {
//        // 获取文件后缀
//        String oName = multipartFile.getOriginalFilename();
//        String extName = oName.substring(oName.lastIndexOf(".") + 1);
//
//        // 文件存放路径
//        String url = null;
//        try {
//            String uploadUrl = storageService.upload(multipartFile.getBytes(), extName);
//            url = FASTDFS_BASE_URL + uploadUrl;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 返回文件完整路径
//        return url;
//    }
//}
