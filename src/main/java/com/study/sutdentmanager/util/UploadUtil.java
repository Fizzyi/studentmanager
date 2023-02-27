package com.study.sutdentmanager.util;

import java.io.File;

public class UploadUtil {
    public final static String IMG_PATH_PREFIX = "static/upload/imgs";

    public static File getImgDirFile(){
        //构建上传文件的文件夹路径
        String fileDirPath = new String("src/main/resources" + IMG_PATH_PREFIX);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            //递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }


}
