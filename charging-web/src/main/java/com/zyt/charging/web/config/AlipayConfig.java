package com.zyt.charging.web.config;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */

import java.io.FileWriter;
import java.io.IOException;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102100734842";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+Lww8B6X1ozwlzu+8ULxve6F83oiF/swi9CsXh7/qaLPB3UdE2F6D5HtLI1gi9HtepEh1gSUYGChfvcMm1O2ozpOnm9ZI3BC3bWU3eolJ49+dE+MYjS6wSTEt4kS7E81h4jUo9XdmMFcV1dnyNz32mNlYwv7eUlDk4Y/K8+HRV1c56LY2oebeeE2Qq2n9N/B/1yStv1GkOQdNjSs/DMjbrnjlQRdI6cyGLDYpj7gfVYjItXPCdHLC4+E8ZQCo2+TnkvA+kXeD4j+4mYO0qWV6j/hro6rdsdJb0k9Q1so1DLwJr5ucPnOpIwe7tiLjiiJ34cJmAFA+rbZWPh0BnoGFAgMBAAECggEAAgmC+gmi73SmKkmDK+M5gYAzhKDlmXnXOfCfnYrotVwVwNfb8ZLXwFXsNCvWv9kakTL1S7zsZsurcwsXUg0p1osP7dWvf+xLiYt0cBmx6sa0GHykDBlHDMBb9H/d+JsHsiq4Iw4uDzwpUqSNxxhsY33Tpi3AiSUclLcN0UC3hISEORubCSsYA4sEsJx7i7C4mzwLYBHBAUpDeTKvgJvAJs0TNTn4YdLuhkyox5ZzUfCrYhabY+Vad+oNVKWHU1sJD0JPFSlB6cb2Dl8JzHy0o9OEUGf37ZwKZZFQEsIThAp35SvkE/wTOI8whz397uBbywmpgPOYdtc45PsFg7dCgQKBgQD5+Pc1WjHwDEMp30iMeR6etwepvy5rGIDrPOhbd14t+itLY1roe63Yat7+rHUQuH7LONaYvEAONiT56mPmlOEq29ZD3+lafwbw36vq4uxBhmSJNwLRwlazxK8zxp6t5VBFFRq5pxG5mQq27wTlsvI7w9QSD1bPWkHUIe7ErRo91QKBgQDCxQRp+Brt1jUN8pmCSOy4o2BlvUnDKBDPrh/QxE7IoOH9Qr7w4efzqFzeaya4w3gsJWPJVilR2piGEAzIEajjgtm49jyCBURk+J7EBhFVWvM5Qxy2+0ayD5W+4yUMOSHd7KMDjY1KPeTdZ9piEdrBuhY+VkLhAbWk3Yxt8Qsc8QKBgQCn5PKVYRk9c98Alrnxr8BDNgftmT0iLXkZhvVByfD1zJubrQX4NGnVfCJ+x3JxYpm3AVwRhg83/GHlgerLip+Z28IUIKZpY/xTeMfNodCxSgTorIw2OuNlJuvOSM93WRgK7lve6jnbgNFTgUK2zPQp9CPSZX8XEjY4tvnaG7IOaQKBgCdYU17wzsfS6N7LbjVCdAeMgXGr/lWdJ2A8xZKPxXaEAYL+08GncQrorPIFZUoXupP4baMDc89kQEF8xog4AQ0MptzVaT2+wKVBEBl7PE8o009y/j78nbhkq5RnIFnKxMum/Ps/whYIUM5lJ1l+T9motMuc81+uz7nhQ3gGJH+RAoGAHrlDWbSqEP8rkkxkpDgpBeztNmzZy02funuqReOue6UBSMocYoyBcwZy7PVMQvNURcgFYIWorktc48GZzX1RjL3UYKRVBxzt28oGvLcpxebe0OZWIdIZBXcwLNeOzQff32PYx008xsj+0erF8HmBaasS4NofsUsBPYrif+wAihY=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/charge";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

