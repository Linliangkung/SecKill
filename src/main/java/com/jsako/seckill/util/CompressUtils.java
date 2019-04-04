package com.jsako.seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Date 2019/3/19
 * @Author LLJ
 * @Description 字符串压缩和解压工具类
 */
public class CompressUtils {

    /**
     * 声明压缩字符串转换成二进制数据的编码
     */
    private static final String DEFAULT_CAHRSET = "UTF-8";

    private static final int DEFAULT_OFF = 0;


    /**
     * 压缩  二进制->二进制
     *
     * @param srcBytes 原始数据
     * @return 压缩后的数据
     */
    public static byte[] compress(byte[] srcBytes) {
        if (Objects.isNull(srcBytes)) {
            return null;
        }
        GZIPOutputStream gzipOutputStream = null;
        ByteArrayOutputStream compressOutputStream = new ByteArrayOutputStream();
        try {
            gzipOutputStream = new GZIPOutputStream(compressOutputStream);
            gzipOutputStream.write(srcBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(gzipOutputStream)) {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return compressOutputStream.toByteArray();
    }

    /**
     * 压缩 字符串->二进制
     *
     * @param src 原始数据
     * @return 压缩后数据
     */
    public static byte[] compress(String src) {

        byte[] compressBytes = null;
        if (StringUtils.isNotBlank(src)) {
            try {
                compressBytes = compress(src.getBytes(DEFAULT_CAHRSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return compressBytes;
    }


    /**
     * 解压  二进制->字符串
     *
     * @param compressBytes 压缩数据
     * @return 解压后的数据
     */
    public static byte[] decompress(byte[] compressBytes) {
        if (Objects.isNull(compressBytes)) {
            return null;
        }
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream srcOutputStream = new ByteArrayOutputStream();
        try {
            gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressBytes));
            byte[] buf = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buf)) != -1) {
                srcOutputStream.write(buf, DEFAULT_OFF, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(gzipInputStream)) {
                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return srcOutputStream.toByteArray();
    }

    /**
     * 解压  二进制->字符串
     *
     * @param compressBytes 压缩数据
     * @return 解压后的数据
     */

    public static String decompress2Str(byte[] compressBytes) {

        String src = null;
        if (Objects.nonNull(compressBytes)) {
            try {
                src = new String(decompress(compressBytes), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return src;
    }
}
