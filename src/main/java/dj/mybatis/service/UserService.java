package dj.mybatis.service;

/**
 * Created by dj on 2020/2/23.
 */
public class UserService {
    public static void main(String[] args) {
    }
    public static int getData(Integer number){
        return number*(number+1);
    }
    public static String helloMethod() {
        return "Hello World!";
    }
    public final String finalMethod() {
        return "Hello World!";
    }
}
