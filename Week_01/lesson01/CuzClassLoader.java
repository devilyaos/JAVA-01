package lesson01;

import java.io.*;
import java.lang.reflect.Method;

/**
 * 自定义构造器, 仅用于demo展示
 * 该自定义构造器仅加载/Week_01/lesson01/目录下的指定xlass文件
 *
 * 在main方法中使用该自定义构造器加载指定的类, 并对字节数组进行指定规则的解码(n = n - 255), 成功加载后, 调用指定的方法进行输出展示
 *
 * @author devilyaos
 */
public class CuzClassLoader extends ClassLoader {

    private static final String SOURCE_DIR = "/Week_01/lesson01/";

    public static void main(String[] args){
        CuzClassLoader cl = new CuzClassLoader();
        try {
            Class<?> clazz = cl.findClass("Hello");
            Method method = clazz.getMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (Exception e) {
            // 此处简要收敛所有异常, 方便demo展示
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 从本地加载xlass文件, 本demo仅加载指定文件
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("文件名称不可为空!");
        }
        String filePath = System.getProperty("user.dir").concat(SOURCE_DIR).concat(name).concat(".xlass");
        try (InputStream is = new FileInputStream(filePath)) {
            byte[] tmpByte = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int n = 0;
            while ((n = is.read(tmpByte)) != -1) {
                out.write(tmpByte, 0, n);
            }
            byte[] fileByte = out.toByteArray();
            for (int i = 0; i < fileByte.length; i++) {
                fileByte[i] = (byte) (255 - fileByte[i]);
            }
            return defineClass(name, fileByte, 0, fileByte.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("读取文件时发生异常");
        }
    }
}
