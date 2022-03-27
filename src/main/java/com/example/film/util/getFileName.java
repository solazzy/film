package com.example.film.util;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java 获取指定文件夹下的所有文件名称工具类
 */
@Service
public  class  getFileName
{
    public   String [] getFileNamePath(String path)
    {
        File file =  new  File(path);
        String [] fileName = file.list();
        return  fileName;
    }
    public    void  getAllFileNamePathAndList(String path, ArrayList<String> fileName)
    {
        File file =  new  File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if (names !=  null )
            fileName.addAll(Arrays.asList(names));
        for (File a:files)
        {
            if (a.isDirectory())
            {
                getAllFileNamePathAndList(a.getAbsolutePath(),fileName);
            }
        }
    }


}
