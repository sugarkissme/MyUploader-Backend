package cn.attackme.myuploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class UploadConfig {

    public static String path;
    public static String linuxPath;
    public static String windPath;



    @Value("${upload.windPath}")
    public void setPath(String path) {
        UploadConfig.windPath = path;
        this.init();
    }

    @Value("${upload.linuxPath}")
    public void setLinuxPath(String path) {
        UploadConfig.linuxPath=path;
        this.init();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name").contains("Win"));
    }

    private void init(){
        boolean win = System.getProperty("os.name").contains("Win");
        File dir = new File(win?UploadConfig.windPath :UploadConfig.linuxPath);
        System.out.println("执行初始化");
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdir();
        }
        UploadConfig.path=win?UploadConfig.windPath :UploadConfig.linuxPath;

    }

}
