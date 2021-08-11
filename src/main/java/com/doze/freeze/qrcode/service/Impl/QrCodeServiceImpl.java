package com.doze.freeze.qrcode.service.Impl;

import com.doze.freeze.qrcode.service.QrCodeService;
import com.doze.freeze.qrcode.utils.QRCodeUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @BelongsProject: qrcode
 * @BelongsPackage: com.doze.freeze.qrcode.service.Impl
 * @Author: hbn
 * @CreateTime: -- :
 * @Description:
 */
@Service("qrCodeService")
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public BufferedImage makeQrCode(int size, String text) throws Exception {
        return QRCodeUtils.createImage(size, text);
    }

    @Override
    public Map<String, String> spotQrCode(MultipartFile file) {
        Map<String, String> result = new HashMap<>( 16);
        String flag = "1";
        String resultStr = "",msg="Success";
        try{
            Result res = QRCodeUtils.spotImage(file);
            resultStr = res.getText();
        }catch (Exception e){
            flag= "0";
            msg = "Recognition error!";
        }finally {
            file=null;
        }
        result.put("code", flag);
        result.put("msg", msg);
        result.put("data", resultStr);
        return result;
    }
}
