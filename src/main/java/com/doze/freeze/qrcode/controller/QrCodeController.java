package com.doze.freeze.qrcode.controller;

import com.doze.freeze.qrcode.service.QrCodeService;
import com.doze.freeze.qrcode.utils.QRCodeUtils;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: qrcode
 * @BelongsPackage: com.doze.freeze.qrcode.controller
 * @Author: hbn
 * @CreateTime: 2021-07-19 23:18
 * @Description:
 */
@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping("/{view}")
    public String view(@PathVariable String view){
        return view;
    }


    @RequestMapping(value = "make",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public BufferedImage makeQrCode1(String text,int size) throws Exception {
        BufferedImage img = qrCodeService.makeQrCode(size,text);
        return img;
    }

    @PostMapping("/spot")
    @ResponseBody
    public Map spotQrCode(@RequestParam("file" ) MultipartFile file) throws NotFoundException {
        return qrCodeService.spotQrCode(file);
    }
}
