package com.joe.code.common.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath
     *            :待压缩的文件路径
     * @param zipFilePath
     *            :压缩后存放路径
     * @param fileName
     *            :压缩后文件的名称
     * @return
     */
    public static boolean folderToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
//            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
//            throw new ToolsException(ToolsExceptionConstant.NOTEXSITERROR_CODE,
//                    String.format(ToolsExceptionConstant.NOTEXSITERROR_MSG, sourceFilePath));
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
//                    zipFile.delete();
//                    zipFile.createNewFile();
//                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
//                    throw new ToolsException(ToolsExceptionConstant.EXSITERROR_CODE,
//                            String.format(ToolsExceptionConstant.EXSITERROR_MSG, zipFilePath, fileName + ".zip"));
                } else {
                    File[] sourceFiles = sourceFile.listFiles();
                    if (null == sourceFiles || sourceFiles.length < 1) {
//                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
//                        throw new ToolsException(ToolsExceptionConstant.EMPTYERROR_CODE,
//                                String.format(ToolsExceptionConstant.EMPTYERROR_MSG, sourceFilePath));
                    } else {
                        fos = new FileOutputStream(zipFile);
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));
                        byte[] bufs = new byte[1024 * 10];
                        for (int i = 0; i < sourceFiles.length; i++) {

                            File file = sourceFiles[i];
                            if(file.isFile()){
                                // 创建ZIP实体，并添加进压缩包
//                                ZipEntry zipEntry = new ZipEntry(file.getName());
//                                zos.putNextEntry(zipEntry);
//                                // 读取待压缩的文件并写进压缩包里
//                                fis = new FileInputStream(file);
//                                bis = new BufferedInputStream(fis, 1024 * 10);
//                                int read = 0;
//                                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
//                                    zos.write(bufs, 0, read);
//                                }
                                compress(file, zos, file.getName(), true);
                            }else{
                                File[] listFiles = file.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
                                if (listFiles == null || listFiles.length == 0) {
                                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
//                                    if (KeepDirStructure) {
                                        // 空文件夹的处理
                                        zos.putNextEntry(new ZipEntry(file.getName() + "/"));
                                        // 没有文件，不需要文件的copy
                                        zos.closeEntry();//关闭当前zip条目读取下一条
//                                    }

                                } else {
//                                    for (File subFile : listFiles) {
                                        // 判断是否需要保留原来的文件结构
//                                        if (KeepDirStructure) {
                                            // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                                            // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                                            compress(file, zos, file.getName(), true);
//                                        } else {
//                                            compress(file, zos, file.getName(), KeepDirStructure);
//                                        }

//                                    }
                                }
                            }

                        }
                        flag = true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                // 关闭流
                try {
                    if (null != bis)
                        bis.close();
                    if (null != zos)
                        zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }

    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws IOException {
        byte[] buf = new byte[1024 * 10];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();//关闭当前zip条目读取下一条
                }

            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }

                }
            }
        }
    }

//
//    /**
//     * 将sourceFilePath文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
//     *
//     * @param sourceFilePath
//     *            :待压缩的文件路径
//     * @param zipFilePath
//     *            :压缩后存放路径
//     * @param fileName
//     *            :压缩后文件的名称
//     * @return
//     */
//    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
//        boolean flag = false;
//        File sourceFile = new File(sourceFilePath);
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        FileOutputStream fos = null;
//        ZipOutputStream zos = null;
//        if (sourceFile.exists() == false) {
//            System.out.println("待压缩的文件：" + sourceFilePath + "不存在.");
//            throw new ToolsException(ToolsExceptionConstant.NOTEXSITERROR_CODE,
//                    String.format(ToolsExceptionConstant.NOTEXSITERROR_MSG, sourceFilePath));
//        } else {
//            try {
//                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
//                if (zipFile.exists()) {
//                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
//                    throw new ToolsException(ToolsExceptionConstant.EXSITERROR_CODE,
//                            String.format(ToolsExceptionConstant.EXSITERROR_MSG, zipFilePath, fileName + ".zip"));
//                } else {
//                    fos = new FileOutputStream(zipFile);
//                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
//                    byte[] bufs = new byte[1024 * 10];
//                    // 创建ZIP实体，并添加进压缩包
//                    ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
//                    zos.putNextEntry(zipEntry);
//                    // 读取待压缩的文件并写进压缩包里
//                    fis = new FileInputStream(sourceFile);
//                    bis = new BufferedInputStream(fis, 1024 * 10);
//                    int read = 0;
//                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
//                        zos.write(bufs, 0, read);
//                    }
//                    flag = true;
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            } finally {
//                // 关闭流
//                try {
//                    if (null != bis)
//                        bis.close();
//                    if (null != zos)
//                        zos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return flag;
//    }

    /**
     * 将流的内容打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param
     *            :待压缩的文件路径
     * @param zipFilePath
     *            :压缩后存放路径
     * @param fileName
     *            :压缩后文件的名称
     * @return
     */
    public static boolean streamToZip(InputStream fis, String streamfilename, String zipFilePath, String fileName) {
        boolean flag = false;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
            if (zipFile.exists()) {
//                System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
//                throw new ToolsException(ToolsExceptionConstant.EXSITERROR_CODE,
//                        String.format(ToolsExceptionConstant.EXSITERROR_MSG, zipFilePath, fileName + ".zip"));
            } else {
                fos = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                byte[] bufs = new byte[1024 * 10];
                // 创建ZIP实体，并添加进压缩包
                ZipEntry zipEntry = new ZipEntry(streamfilename);
                zos.putNextEntry(zipEntry);
                // 读取待压缩的文件并写进压缩包里
                bis = new BufferedInputStream(fis, 1024 * 10);
                int read = 0;
                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                    zos.write(bufs, 0, read);
                }
                flag = true;
            }
            zos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 关闭流
            try {
                if (null != bis)
                    bis.close();
                if (null != zos)
                    zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }

//    /**
//     * 将流转成zip文件输出
//     * @param inputstream
//     *            文件流
//     * @param streamfilename
//     *            流文件的名称
//     * @param fileName zip包的名称
//     * @param response
//     * @return
//     */
//    public static boolean streamToZipStream(InputStream inputstream, String streamfilename, String fileName,
//                                            HttpServletResponse response) {
//        boolean flag = false;
//        BufferedInputStream bis = null;
//        FileOutputStream fos = null;
//        ZipOutputStream zos = null;
//        OutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            response.reset();
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setCharacterEncoding("UTF-8");
//            zos = new ZipOutputStream(out);
//            byte[] bufs = new byte[1024 * 10];
//            // 创建ZIP实体，并添加进压缩包
//            ZipEntry zipEntry = new ZipEntry(streamfilename);
//            zos.putNextEntry(zipEntry);
//            // 读取待压缩的文件并写进压缩包里
//            bis = new BufferedInputStream(inputstream, 1024 * 10);
//            int read = 0;
//            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
//                zos.write(bufs, 0, read);
//            }
//            flag = true;
//            zos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            // 关闭流
//            try {
//                if (null != bis)
//                    bis.close();
//                if (null != zos)
//                    zos.close();
//                if (null != out)
//                    out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 将多个流转成zip文件输出
//     * @param listStream
//     *            文件流实体类对象
//     * @param fileName zip包的名称
//     * @param response
//     * @return
//     */
//    public static boolean listStreamToZipStream(List<ZipStreamEntity> listStream, String fileName, HttpServletResponse response) {
//        boolean flag = false;
//        BufferedInputStream bis = null;
//        FileOutputStream fos = null;
//        ZipOutputStream zos = null;
//        OutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            response.reset();
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setCharacterEncoding("UTF-8");
//            zos = new ZipOutputStream(out);
//            byte[] bufs = new byte[1024 * 10];
//            for (ZipStreamEntity zipstream : listStream) {
//                String streamfilename = StringUtils.isnull(zipstream.getName());
//                // 创建ZIP实体，并添加进压缩包
//                ZipEntry zipEntry = new ZipEntry(streamfilename);
//                zos.putNextEntry(zipEntry);
//                // 读取待压缩的文件并写进压缩包里
//                bis = new BufferedInputStream(zipstream.getInputstream(), 1024 * 10);
//                int read = 0;
//                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
//                    zos.write(bufs, 0, read);
//                }
//            }
//            flag = true;
//            zos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            // 关闭流
//            try {
//                if (null != bis)
//                    bis.close();
//                if (null != zos)
//                    zos.close();
//                if (null != out)
//                    out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
//        return flag;
//    }

    public static void main(String[] args) throws FileNotFoundException {
        String sourceFilePath = "D:\\test\\ SYSUSER_1703_20180130.csv";

        File file = new File(sourceFilePath);
        InputStream fileInputStream = new FileInputStream(file);

        System.out.println(file.exists());
        String zipFilePath = "D:\\test";
        String fileName = "232wdsadwada";
        String streamfileName = "wwww.csv";
        // boolean flag = ZipUtils.fileToZip(sourceFilePath, zipFilePath, fileName);
        boolean flag = ZipUtils.streamToZip(fileInputStream, streamfileName, zipFilePath, fileName);
        if (flag) {
            System.out.println("文件打包成功!");
        } else {
            System.out.println("文件打包失败!");
        }
    }
}
