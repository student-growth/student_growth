package com.info.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 拼接路径
     * concatPath("/mnt/sdcard", "/DCIM/Camera")  	=>		/mnt/sdcard/DCIM/Camera
     * concatPath("/mnt/sdcard", "DCIM/Camera")  	=>		/mnt/sdcard/DCIM/Camera
     * concatPath("/mnt/sdcard/", "/DCIM/Camera")  =>		/mnt/sdcard/DCIM/Camera
     */
    public static String concatPath(String... paths) {
        StringBuilder result = new StringBuilder();
        if (paths != null) {
            for (String path : paths) {
                if (path != null && path.length() > 0) {
                    int len = result.length();
                    boolean suffixSeparator = len > 0 && result.charAt(len - 1) == File.separatorChar;//后缀是否是'/'
                    boolean prefixSeparator = path.charAt(0) == File.separatorChar;//前缀是否是'/'
                    if (suffixSeparator && prefixSeparator) {
                        result.append(path.substring(1));
                    } else if (!suffixSeparator && !prefixSeparator) {//补前缀
                        result.append(File.separatorChar);
                        result.append(path);
                    } else {
                        result.append(path);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 计算文件的md5值
     */
    public static String calculateMD5(File updateFile) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LogUtil.error("Exception while getting digest");
            return null;
        }

        InputStream is;
        try {
            is = new FileInputStream(updateFile);
        } catch (FileNotFoundException e) {
            return null;
        }

        //DigestInputStream

        byte[] buffer = new byte[8192];
        int read;
        try {
            while ((read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            // Fill to 32 chars
            output = String.format("%32s", output).replace(' ', '0');
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LogUtil.error("Exception on closing MD5 input stream");
            }
        }
    }

    /**
     * 计算文件的md5值
     */
    public static String calculateMD5(File updateFile, int offset, int partSize) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LogUtil.error("Exception while getting digest");
            return null;
        }

        InputStream is;
        try {
            is = new FileInputStream(updateFile);
        } catch (FileNotFoundException e) {
            LogUtil.error("Exception while getting FileInputStream");
            return null;
        }

        //DigestInputStream
        final int buffSize = 8192;//单块大小
        byte[] buffer = new byte[buffSize];
        int read;
        try {
            if (offset > 0) {
                is.skip(offset);
            }
            int byteCount = Math.min(buffSize, partSize), byteLen = 0;
            while ((read = is.read(buffer, 0, byteCount)) > 0 && byteLen < partSize) {
                digest.update(buffer, 0, read);
                byteLen += read;
                //检测最后一块，避免多读数据
                if (byteLen + buffSize > partSize) {
                    byteCount = partSize - byteLen;
                }
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            // Fill to 32 chars
            output = String.format("%32s", output).replace(' ', '0');
            return output;
        } catch (IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LogUtil.error("Exception on closing MD5 input stream");
            }
        }
    }

    /**
     * 检测文件是否可用
     */
    public static boolean checkFile(File f) {
        if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0))) {
            return true;
        }
        return false;
    }

    /**
     * 检测文件是否可用
     */
    public static boolean checkFile(String path) {
        if (!StringUtil.isEmpty(path)) {
            File f = new File(path);
            if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0)))
                return true;
        }
        return false;
    }

    /**
     计算文件或者文件夹的大小 ，单位 KB

     @param file 要计算的文件或者文件夹 ， 类型：java.io.File
     @return 大小，单位：KB
     */
    public static double getFileSize(File file) {
        //判断文件是否存在
        if (file == null) {
            return 0.0;
        }
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
            if (!file.isFile()) {
                //获取文件大小
                File[] fl = file.listFiles();
                double ss = 0;
                if (fl != null) {
                    for (File f : fl)
                        ss += getFileSize(f);
                }
                return ss;
            } else {
                double ss = (double) file.length() / 1024;
                System.out.println(file.getName() + " : " + ss + "KB");
                return ss;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

    public static long getFileSize(String fn) {
        File f = null;
        long size = 0;

        try {
            f = new File(fn);
            size = f.length();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            f = null;
        }
        return size < 0 ? null : size;
    }

    public static String getFileType(String fn, String defaultType) {
        FileNameMap fNameMap = URLConnection.getFileNameMap();
        String type = fNameMap.getContentTypeFor(fn);
        return type == null ? defaultType : type;
    }

    public static String getFileType(String fn) {
        return getFileType(fn, "application/octet-stream");
    }

    public static String getFileExtension(String filename) {
        String extension = "";
        if (filename != null) {
            int dotPos = filename.lastIndexOf(".");
            if (dotPos >= 0 && dotPos < filename.length() - 1) {
                extension = filename.substring(dotPos + 1);
            }
        }
        return extension.toLowerCase();
    }

    public static boolean deleteFile(File f) {
        if (f != null && f.exists() && !f.isDirectory()) {
            return f.delete();
        }
        return false;
    }

    public static void deleteDir(File f) {
        if (f != null && f.exists() && f.isDirectory()) {
            for (File file : f.listFiles()) {
                if (file.isDirectory())
                    deleteDir(file);
                file.delete();
            }
            f.delete();
        }
    }

    public static void deleteCacheFile(String f) {
        if (f != null && f.length() > 0) {
            File files = new File(f);
            if (files.exists() && files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (!file.isDirectory() && (file.getName().contains(".ts") || file.getName().contains("temp"))) {
                        file.delete();
                    }

                }
            }
        }
    }
    public static void deleteCacheFile2TS(String f) {
        if (f != null && f.length() > 0) {
            File files = new File(f);
            if (files.exists() && files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (!file.isDirectory() && (file.getName().contains(".ts"))) {
                        file.delete();
                    }

                }
            }
        }
    }
    public static void deleteDir(String f) {
        if (f != null && f.length() > 0) {
            deleteDir(new File(f));
        }
    }

    public static boolean deleteFile(String f) {
        if (f != null && f.length() > 0) {
            return deleteFile(new File(f));
        }
        return false;
    }

    /**
     * read file
     *
     * @param charsetName The name of a supported {@link java.nio.charset.Charset
     *                    </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
    public static String readFile(File file, String charsetName) {
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return fileContent.toString();
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
        return fileContent.toString();
    }

    public static String readFile(String filePath, String charsetName) {
        return readFile(new File(filePath), charsetName);
    }

    public static String readFile(File file) {
        return readFile(file, "utf-8");
    }

    /**
     * 文件拷贝
     *
     * @param from
     * @param to
     * @return
     */
    public static boolean fileCopy(String from, String to) {
        boolean result = false;

        int size = 1 * 1024;

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);
            byte[] buffer = new byte[size];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
        return result;
    }

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String path){
        List<String> list = null;
        FileInputStream fis=null;
        InputStreamReader isr =null;
        BufferedReader br =null;
        try {
            File file=new File(path);
            if(!file.exists()){//判断文件是否存在
                //文件不存在
                logger.error("文件不存在..."+path);
                return list;
            }
            String filecharset = getFilecharset(file);
            // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
            list = new ArrayList<String>();
            fis = new FileInputStream(path);
            // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
            isr = new InputStreamReader(fis, filecharset);
            br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
                if (line!=null&&line.length()>0) {
                    list.add(line);
                }
            }
        }catch (Exception e){
            logger.error("文件读取异常..."+path, e);
            e.printStackTrace();
        }finally {
            try {
                if(null!=br){
                    br.close();
                }
                if(null!=isr){
                    isr.close();
                }
                if(null!=fis){
                    fis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return list;
    }

    /**
     * 读取一个文本 一行一行读取
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFileByLine(String path,String charsetName){
        List<String> list = null;
        FileInputStream fis=null;
        InputStreamReader isr =null;
        BufferedReader br =null;
        try {
            File file=new File(path);
            if(!file.exists()){//判断文件是否存在
                //文件不存在
                logger.error("文件不存在..."+path);
                return list;
            }
            // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
            list = new ArrayList<String>();
            fis = new FileInputStream(path);
            // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
            isr = new InputStreamReader(fis, charsetName);
            br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
                if (line!=null&&line.length()>0) {
                    list.add(line);
                }
            }
        }catch (Exception e){
            logger.error("文件读取异常..."+path, e);
            e.printStackTrace();
        }finally {
            try {
                if(null!=br){
                    br.close();
                }
                if(null!=isr){
                    isr.close();
                }
                if(null!=fis){
                    fis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return list;
    }

    public static void moveFile(String pathName, String fileName, String ansPath, String asnFileName){
        String startPath =  pathName + File.separator + fileName;
        String endPath = ansPath;
        try {
            File startFile = new File(startPath);
            File tmpFile = new File(endPath);//获取文件夹路径
            if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
                tmpFile.mkdirs();
            }
            if (startFile.renameTo(new File(endPath + asnFileName))) {
                System.out.println("File is moved successful!");
                logger.info("文件移动成功！文件名：《{}》 目标路径：{}"+fileName+endPath);
            } else {
                System.out.println("File is failed to move!");
                logger.error("文件移动失败！文件名：《{}》 起始路径：{}"+fileName+startPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件移动异常！文件名：《{}》 起始路径：{}"+fileName+startPath,e);

        }
    }

    public static void moveFile(String srcPathName, String ansPathName){
        try {
            File startFile = new File(srcPathName);
            if (startFile.renameTo(new File(ansPathName))) {
                System.out.println("File is moved successful!");
                logger.info("文件移动成功！文件名：《{}》 目标路径：{}"+srcPathName+ansPathName);
            } else {
                System.out.println("File is failed to move!");
                logger.error("文件移动失败！文件名：《{}》 起始路径：{}"+srcPathName+ansPathName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件移动异常！文件名：《{}》 起始路径：{}"+srcPathName+ansPathName,e);

        }
    }

    /**
     * 追写文件
     * @param lineContent
     */
    public static void writeFile(String lineContent, String filePath){
        FileOutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            File outputFile = new File(filePath);
            File fileParent = outputFile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            String filecharset = getFilecharset(outputFile);
            out = new FileOutputStream(outputFile, true);
            writer = new OutputStreamWriter(out, filecharset);
            bw = new BufferedWriter(writer);
            bw.write(lineContent);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            logger.error(filePath,e);
            e.printStackTrace();
        }finally{
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 追写文件
     * @param lineContent
     */
    public synchronized static void writeFileSync(String lineContent, String filePath){
        FileOutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            File outputFile = new File(filePath);
            File fileParent = outputFile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            String filecharset = getFilecharset(outputFile);
            out = new FileOutputStream(outputFile, true);
            writer = new OutputStreamWriter(out, filecharset);
            bw = new BufferedWriter(writer);
            bw.write(lineContent);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            logger.error(filePath,e);
            e.printStackTrace();
        }finally{
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 追写文件
     * @param lineContent
     */
    public static void writeFile(String lineContent, String filePath,String charsetName){
        FileOutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            File outputFile = new File(filePath);
            File fileParent = outputFile.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            out = new FileOutputStream(outputFile, true);
            writer = new OutputStreamWriter(out, charsetName);
            bw = new BufferedWriter(writer);
            bw.write(lineContent);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            logger.error(filePath,e);
            e.printStackTrace();
        }finally{
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     * @param fileNamePath
     */
    public static void removeFile(String fileNamePath){
        try {
            File file = new File(fileNamePath);
            if(file.exists()){
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入TXT，覆盖原内容
     * @param content
     * @param fileNamePath
     * @return
     * @throws Exception
     */
    public static boolean writeTxtFile(String content,String fileNamePath)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        File file = new File(fileNamePath);
        FileOutputStream fileOutputStream=null;
        try {
            String filecharset = getFilecharset(file);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes(filecharset));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 写入TXT，覆盖原内容
     * @param content
     * @param fileNamePath
     * @return
     * @throws Exception
     */
    public static boolean writeTxtFile(String content,String fileNamePath,String charsetName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        File file = new File(fileNamePath);
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes(charsetName));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 删除文本中前几行
     * @return
     */
    public static boolean delTextFileContent(int lineDel,String fileNamePath){
        boolean flag=false;
        BufferedReader br=null;
        BufferedWriter bw=null;
        try {
            br=new   BufferedReader(new FileReader( fileNamePath));
            StringBuffer sb=new   StringBuffer(4096);
            String   temp=null;
            int   line=0;
            while((temp=br.readLine())!=null){
                line++;
                if(line<=lineDel)   {
                    continue;
                }
                sb.append(temp).append( "\r\n");
            }
            bw=new   BufferedWriter(new FileWriter( fileNamePath));
            bw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw!=null){
                    bw.close();
                }
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    /**
     * 读取文本多少行
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> readFileLine(int lineNum,String path){
        List<String> list = null;
        FileInputStream fis=null;
        InputStreamReader isr =null;
        BufferedReader br =null;
        try {
            File file=new File(path);
            if(!file.exists()){//判断文件是否存在
                //文件不存在
                logger.error("文件不存在..."+path);
                return list;
            }
            String filecharset = getFilecharset(file);
            // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
            list = new ArrayList<String>();
            fis = new FileInputStream(path);
            // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
            isr = new InputStreamReader(fis,filecharset);
            br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
                if (line!=null&&line.length()>0) {
                    if(list.size()>=lineNum){
                        break;
                    }else {
                        list.add(line);
                    }

                }
            }
        }catch (Exception e){
            logger.error("文件读取异常..."+path, e);
            e.printStackTrace();
        }finally {
            try {
                if(null!=br){
                    br.close();
                }
                if(null!=isr){
                    isr.close();
                }
                if(null!=fis){
                    fis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return list;
    }

    /**
     *  获取文件行数
     * @param fileNamePath 文件路径
     * @return
     */
    public int findFileLine(String fileNamePath){
        int linenumber=0;
        try{
            File file =new File(fileNamePath);
            if(file.exists()){
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                linenumber = 0;
                while (lnr.readLine() != null){
                    linenumber++;
                }
                logger.info("Total number of lines : " + linenumber);
                lnr.close();
            }else{
                logger.info("File does not exists!");
            }
        }catch(IOException e){
            e.printStackTrace();
            logger.error("获取文件行数失败...",e);
        }
        return linenumber;
    }

    /**
     * 获取文件编码
     *
     * @param sourceFile
     * @return
     */
    private static String getFilecharset(File sourceFile) {
        byte[] first3Bytes = new byte[3];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile))) {
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return "GBK"; // 文件编码为 ANSI
            }

            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                return "UTF-16LE"; // 文件编码为 Unicode
            }

            if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                return "UTF-16BE"; // 文件编码为 Unicode big endian
            }

            if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                return "UTF-8"; // 文件编码为 UTF-8
            }

            bis.reset();

            while ((read = bis.read()) != -1) {
                if (read >= 0xF0) {
                    break;
                }
                if (0x80 <= read && read <= 0xBF) {
                    break;
                }
                if (0xC0 <= read && read <= 0xDF) {
                    read = bis.read();
                    if (0x80 <= read && read <= 0xBF) {
                        // (0x80 - 0xBF),也可能在GB编码内
                        continue;
                    }

                    break;
                } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                    read = bis.read();
                    if (0x80 <= read && read <= 0xBF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            return "UTF-8";
                        }
                        break;
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "GBK";
    }
}
