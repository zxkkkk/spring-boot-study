package top.zxk.springboot.filter_interceptor.utils;

import cn.hutool.core.img.ImgUtil;
import com.aliyun.oss.OSS;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.zxk.springboot.filter_interceptor.config.OssConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.UUID;

@Component
public class OssUtil {
    @Resource
    private OSS ossClient;

    @Resource
    private OssConfig ossConfig;

    public String uploadFile(MultipartFile file) {
        try {
            // 1. 检查文件名
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("文件名称不能为空");
            }

            // 2. 生成唯一的文件名
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + fileExtension;

            // 3. 处理文件（如添加水印）
            BufferedImage image = ImageIO.read(file.getInputStream());
            // 在这里可以进行图片处理，如添加水印
            // 例如：
            Image watermarkedImage = ImgUtil.pressText(image, " zxk ", Color.WHITE,
                    new Font("黑体", Font.BOLD, 60), 10, 20, 0.5f);
            BufferedImage watermarkedBufferedImage = new BufferedImage(
                    watermarkedImage.getWidth(null),
                    watermarkedImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB
            );
            Graphics2D g = watermarkedBufferedImage.createGraphics();
            g.drawImage(watermarkedImage, 0, 0, null);
            g.dispose();

            // 4. 将处理后的图片写入输出流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(watermarkedBufferedImage, fileExtension.replace(".", ""), outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // 5. 上传文件到OSS
            ossClient.putObject(ossConfig.getBucketName(), fileName, inputStream);

            // 6. 构建文件的URL路径
            return "https://" + ossConfig.getBucketName() + "." +
                    ossConfig.getEndpoint().replace("https://", "") + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
        }
    }
}
