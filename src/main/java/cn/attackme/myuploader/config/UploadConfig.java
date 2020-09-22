package cn.attackme.myuploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class UploadConfig {

    public static String path;
    public static String linuxPath;
    public static String windPath;
    public static String url;



    @Value("${upload.windPath}")
    public void setPath(String path) {
        UploadConfig.windPath = path;

    }
    @Value("${upload.url}")
    public void setUrl(String url) {
        UploadConfig.url = url;

    }

    @Value("${upload.linuxPath}")
    public void setLinuxPath(String linuxPath) {
        UploadConfig.linuxPath=linuxPath;
        this.init();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name").contains("Win"));
    }

    private void init(){
        String system = System.getProperty("os.name");
        boolean win=false;
        if(system!=null&&system.contains("Win")){
            win=true;
        }
        System.out.println("加载系统:"+system+"---win:"+UploadConfig.windPath+"---linux:"+UploadConfig.linuxPath);
        File dir = new File(win?UploadConfig.windPath :UploadConfig.linuxPath);
        System.out.println("执行初始化:"+dir.exists());
        if (!dir.exists()) {// 判断目录是否存在
            boolean mkdir = dir.mkdir();
            System.out.println("创建目录执行结果："+mkdir+" 路径"+UploadConfig.linuxPath);

        }
        UploadConfig.path=win?UploadConfig.windPath :UploadConfig.linuxPath;

    }

}
