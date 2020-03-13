package dj.mybatis.service;

import dj.mybatis.entity.Person;
import lombok.Synchronized;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by dj on 2020/2/23.
 */
@Service
public class UserService {
    /**
     * 服务对象
     */
    @Resource
    private PersonService personService;
    public static void main(String[] args) {
    }

    public static int getData(Integer number) {
        return number * (number + 1);
    }

    public static String helloMethod() {
        return "Hello World!";
    }
    @Async
    public  String hello() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
        return "Hello World!";
    }
    @Transactional
    public Person update(Person person){
            return this.personService.update(person);
    }

    public final String finalMethod() {
        return "Hello World!";
    }

}

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    @Synchronized
    public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
        return instance;
    }
}

