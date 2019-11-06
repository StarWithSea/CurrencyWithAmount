package com.fileOperation;

import com.constant.Constants;
import com.validate.Validates;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

public class FileUtil {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    private volatile static FileUtil fileUtil;

    private FileUtil(){}

    public static FileUtil getFileUtil(){
        if(fileUtil == null){
            synchronized (Validates.class){
                if(fileUtil == null){
                    fileUtil = new FileUtil();
                }
            }
        }
        return fileUtil;
    }

    public List<String> getFileData(String path) throws IOException {
        rwLock.writeLock().lock();
        List<String> result = new ArrayList<String>();
        try {

            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (Pattern.matches(Constants.CURRENCY_CODE_REGEX, line)) {
                    result.add(line);
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            rwLock.writeLock().unlock();
        }
        return result;
    }
}
