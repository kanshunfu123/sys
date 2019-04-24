package com.xiaowei.web.controller.restful;

import com.xiaowei.common.util.yidong.YDUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MOMO on 2019/4/8.
 */
@RestController
@RequestMapping(value = "/platform/yd")
@Slf4j
public class YidongController {
    private static String token ="abcdefghijkmlnopqrstuvwxyz";//用户自定义token和OneNet第三方平台配置里的token一致
    private static String aeskey ="rL8UT74bi7F66hSVzf40Fc59ZKjZeba18hTolzXm2Pr";//aeskey和OneNet第三方平台配置里的token一致

    private static Logger logger = LoggerFactory.getLogger(YidongController.class);
    /**
     * 功能描述：第三方平台数据接收。<p>
     *           <ul>注:
     *               <li>1.OneNet平台为了保证数据不丢失，有重发机制，如果重复数据对业务有影响，数据接收端需要对重复数据进行排除重复处理。</li>
     *               <li>2.OneNet每一次post数据请求后，等待客户端的响应都设有时限，在规定时限内没有收到响应会认为发送失败。
     *                    接收程序接收到数据时，尽量先缓存起来，再做业务逻辑处理。</li>
     *           </ul>
     * @param body 数据消息
     * @return 任意字符串。OneNet平台接收到http 200的响应，才会认为数据推送成功，否则会重发。
     */
    @RequestMapping(value = "/receive",method = RequestMethod.POST)
    @ResponseBody
    public String receive(@RequestBody String body) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

//        logger.debug("明文模式  --- begin" );
//        logger.debug("data receive:  body String --- " +body);
//        /************************************************
//         *  解析数据推送请求，非加密模式。
//         *  如果是明文模式使用以下代码
//         **************************************************/
//        /*************明文模式  start****************/
//        YDUtil.BodyObj obj = YDUtil.resolveBody(body, false);
//        logger.debug("data receive:  body Object --- " +obj);
//        if (obj != null){
//            boolean dataRight = YDUtil.checkSignature(obj, token);
//            if (dataRight){
//                logger.debug("data receive: content" + obj.toString());
//            }else {
//                logger.debug("data receive: signature error");
//            }
//
//        }else {
//            logger.debug("data receive: body empty error");
//        }
//        logger.debug("明文模式  --- end" );
        /*************明文模式  end****************/


        /********************************************************
         *  解析数据推送请求，加密模式
         *
         *  如果是加密模式使用以下代码
         ********************************************************/
        /*************加密模式  start****************/
        logger.debug("加密模式  --- begin" );
        logger.error("加密模式  --- begin" );
        YDUtil.BodyObj obj1 = YDUtil.resolveBody(body, true);
        logger.debug("data receive:  body Object--- " +obj1);
        logger.error("data receive:  body Object--- " +obj1);
        if (obj1 != null){
            boolean dataRight1 = YDUtil.checkSignature(obj1, token);
            if (dataRight1){
                String msg = YDUtil.decryptMsg(obj1, aeskey);
                logger.debug("data receive: content" + msg);
                logger.error("data receive: content" + msg);
            }else {
                logger.debug("data receive:  signature error " );
                logger.error("data receive:  signature error " );
            }
        }else {
            logger.debug("data receive: body empty error" );
            logger.error("data receive: body empty error" );
        }
        logger.debug("加密模式  --- end" );
        logger.error("加密模式  --- end" );
        /*************加密模式  end****************/
        return "ok";
    }

    /**
     * 功能说明： URL&Token验证接口。如果验证成功返回msg的值，否则返回其他值。
     * @param msg 验证消息
     * @param nonce 随机串
     * @param signature 签名
     * @return msg值
     */

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public String check(@RequestParam(value = "msg") String msg,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "signature") String signature) throws UnsupportedEncodingException {

        logger.debug("url&token check: msg:{} nonce{} signature:{}",msg,nonce,signature);
        if (YDUtil.checkToken(msg,nonce,signature,token)){
            return msg;
        }else {
            return "error";
        }

    }


}
