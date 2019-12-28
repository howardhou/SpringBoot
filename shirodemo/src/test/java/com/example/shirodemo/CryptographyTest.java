package com.example.shirodemo;

import com.example.shirodemo.realm.MyAuthorizingRealm2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.security.Key;

// Cryptography 密码术、密码系统
@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptographyTest {

    //  base64 编码 / 解码操作
    @Test
    public void testBase64(){
        String str = "hello world";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.printf("base64Encoded : " + base64Encoded + "\n");
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str, str2);
    }

    //  16 进制字符串编码 / 解码操作
    @Test
    public void testHex(){
        String str = "hello";
        String hexEncoded = Hex.encodeToString(str.getBytes());
        System.out.printf("hexEncoded : " + hexEncoded + "\n");
        String str2 = new String(Hex.decode(hexEncoded.getBytes()));
        Assert.assertEquals(str, str2);
    }

    @Test
    public void testHash(){
        String str = "hello";
        String salt = "123";

        // 使用 Md5Hash 算法生成相应的散列数据
        String md5 = new Md5Hash(str, salt).toString();
        System.out.printf("md5 : " + md5 + "\n");

        // 使用 SHA256 算法生成相应的散列数据
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.printf("sha256 : " + sha1 + "\n");

        // 使用 simpleHash 算法生成相应的散列数据
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.printf("simpleHash SHA-1 : " + simpleHash + "\n");


        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello hi"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();

        String hex = hashService.computeHash(request).toHex();
        System.out.printf("hashService SHA-512 : " + hex + "\n");
    }

    @Test
    public void testAES(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置key长度
//生成key
        Key key = aesCipherService.generateNewKey();
        System.out.printf("aes key : " + key + "\n");
        String text = "hello";
//加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.printf("aes encrptText : " + encrptText + "\n");
//解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        Assert.assertEquals(text, text2);
    }

    private Subject login(String iniPath, String username, String password){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        // 对主体对象进行认证
        // 用户登陆
        // 设置用户认证的身份(principals)和凭证(credentials)
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        return subject;
    }

    // Shiro 身份验证 : https://www.w3cschool.cn/shiro/xgj31if4.html
    @Test
    public void testPasswordService() {

        Subject subject = login("classpath:shiro-passwordservice.ini", "admin", "123456");

        String name = subject.getPrincipal().toString();

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }


    @Test
    public void testEncodePassword(){
        MyAuthorizingRealm2.getEncodePassword();
    }

    @Test
    public void testHashedCredentialsMatcher() {

        Subject subject = login("classpath:shiro-hashedCredentialsMatcher.ini", "admin", "123456");

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }


}
