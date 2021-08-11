package com.doze.freeze.qrcode.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @BelongsProject: qrcode
 * @BelongsPackage: com.doze.freeze.qrcode.service
 * @Author: hbn
 * @CreateTime: 2021-07-19 23:40
 * @Description:
 */

public interface QrCodeService {
    BufferedImage makeQrCode(int size, String text) throws Exception;

    Map<String, String> spotQrCode(MultipartFile file);
}
