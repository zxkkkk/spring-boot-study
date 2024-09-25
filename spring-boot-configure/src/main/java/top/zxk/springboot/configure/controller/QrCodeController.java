package top.zxk.springboot.configure.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zxk.springboot.configure.service.QrCodeService;

@RestController
@AllArgsConstructor
public class QrCodeController {
    private final QrCodeService qrCodeService;

    @GetMapping("/qrcode")
    public void qrcode(){
        qrCodeService.generateQrCode();
    }
}
