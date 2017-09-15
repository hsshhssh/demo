package com.xqh.financial.demo;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Demo
{
    public static void main(String[] args)
    {
        int nowTime = (int) (System.currentTimeMillis()/1000);
        String key = args[3]; // 秘钥


        String baseUrl = "http://139.196.51.152:8080/xqh/financial/pay";
        String payUserId = args[0]; // 用户id
        String appId = args[1]; // 应用id
        String money = args[2]; // 支付金额 单位：分
        int time = nowTime; // 当前时间
        String sign = getMd5("" + payUserId + appId + money + time + key); // 确保是字符串拼接
        int payType = 1; // 支付方式
        String userOrderNo = "testeOrderNo"; // 商户订单号
        String userParam = "testuserParam"; // 商户自定义参数

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(baseUrl);
        stringBuffer.append("?payUserId=" + payUserId);
        stringBuffer.append("&appId=" + appId);
        stringBuffer.append("&money=" + money);
        stringBuffer.append("&time=" + time);
        stringBuffer.append("&sign=" + sign);
        stringBuffer.append("&payType=" + args[4]);
        stringBuffer.append("&userOrderNo=" + userOrderNo);
        stringBuffer.append("&userParam=" + userParam);

        System.out.println(stringBuffer.toString());

    }

    /**
     * md5 方法
     * @param plainText
     * @return
     */
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
}



















