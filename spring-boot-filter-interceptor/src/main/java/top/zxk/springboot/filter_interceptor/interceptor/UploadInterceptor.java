package top.zxk.springboot.filter_interceptor.interceptor;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class UploadInterceptor implements HandlerInterceptor {
    private static final long MAX_SIZE = 5 * 1024 * 1024;
    private static final String[] TYPES = { "image/png", "image/jpg" };
    private static final int MAX_WIDTH = 1980;
    private static final int MAX_HEIGHT = 1080;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request instanceof MultipartHttpServletRequest)) {
            response.getWriter().write("请求类型错误，未包含文件");
            return false;
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        // 1. 文件类型检查
        String fileType = file.getContentType();
        if (!StrUtil.containsAny(fileType, TYPES)) {
            response.getWriter().write("文件类型错误");
            return false;
        }

        // 2. 文件大小限制
        if (file.getSize() > MAX_SIZE) {
            response.getWriter().write("上传文件大小超出限制");
            return false;
        }

        // 3. 图片尺寸检查
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image.getWidth() > MAX_WIDTH || image.getHeight() > MAX_HEIGHT) {
            response.getWriter().write("图片尺寸超出限制");
            return false;
        }

        // 4. 生成唯一文件名，防止覆盖
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            response.getWriter().write("文件名称不能为空");
            return false;
        }
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString() + "_" + originalFilename;

        // 5. 添加水印
        Image originalImage = ImageIO.read(file.getInputStream());
        Image watermarkedImage = ImgUtil.pressText(originalImage, " zxk ", Color.WHITE,
                new Font("黑体", Font.BOLD, 60), 10, 20, 0.5f);

        // 将带水印的图片转换为BufferedImage
        BufferedImage watermarkedBufferedImage = new BufferedImage(
                watermarkedImage.getWidth(null),
                watermarkedImage.getHeight(null),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g = watermarkedBufferedImage.createGraphics();
        g.drawImage(watermarkedImage, 0, 0, null);
        g.dispose();

        // 将带水印的图片写入输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(watermarkedBufferedImage, fileExtension.replace(".", ""), outputStream);
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        // 上传文件到OSS
        try {
        } catch (Exception e) {
        } finally {
            // 关闭输入流以释放资源
            inputStream.close();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器响应处理完毕：path:{}, endTime={}", request.getRequestURI(), LocalDateTime.now());
    }
}
