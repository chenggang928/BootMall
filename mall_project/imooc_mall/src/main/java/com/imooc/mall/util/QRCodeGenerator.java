package com.imooc.mall.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.imooc.mall.common.Constant;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * 生成二维码工具
 */
public class QRCodeGenerator {
    public static void generateQRCodeImage(String text,int width,int heigeht,String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, heigeht);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);

    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("Hello World",350,350, "D:/Desktop/mall_project/imooc_mall_prepare_static/QRTest.png");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
