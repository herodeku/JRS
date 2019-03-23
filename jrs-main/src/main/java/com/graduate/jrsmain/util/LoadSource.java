package com.graduate.jrsmain.util;
import com.alibaba.fastjson.JSONArray;
import com.graduate.jrsmain.bean.Judgment;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadSource {

    public static List<Judgment> loadToCivil(String fileName){
        File file = new File(fileName);
        String input = null;
        try {
            input = FileUtils.readFileToString(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Judgment> jsonArray = JSONArray.parseArray(input,Judgment.class);
        return jsonArray;
    }
//    public static void main(String[] args) {
//        new LoadSource().loadToCivil("C:\\Users\\win\\Desktop\\civil.txt");
//    }
}
