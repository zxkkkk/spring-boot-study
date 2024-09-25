//package top.zxk.springboot.configure.util;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import top.zxk.springboot.configure.config.OssConfig;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//
//@Component
//public class OssTemplate {
//    @Resource
//    private OssConfig ossConfig;
//
//    public String ossUpload(MultipartFile file) {
//        String endpoint = ossConfig.getEndpoint();
//        String bucket = ossConfig.getBucket();
//        String dir = ossConfig.getDir();
//        String host = ossConfig.getHost();
//        String ak = ossConfig.getAk();
//        String secret = ossConfig.getSecret();
//        // 创建 OSSClient 实例
//        OSS ossClient = new OSSClientBuilder().build(endpoint, ak, secret)
//                ;
//        // 上传⽂件存放的路径
//        String uploadPath = dir + file.getOriginalFilename();
//        // 获取⽂件的字节输⼊流
//        InputStream inputStream = null;
//        try {
//            inputStream = file.getInputStream();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 调⽤ SDK 上传⽂件
//        ossClient.putObject(bucket, uploadPath, inputStream);
//        // 关闭 ossClient
//        ossClient.shutdown();
//        // 返回上传⽂件的 url
//        return host + "/" + uploadPath;
//    }
//}