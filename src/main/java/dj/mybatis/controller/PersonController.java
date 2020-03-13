package dj.mybatis.controller;

import dj.mybatis.entity.Person;
import dj.mybatis.service.PersonService;
import dj.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Person)表控制层
 *
 * @author makejava
 * @since 2020-03-04 14:22:02
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
     UserService userService;
    /**
     * 服务对象
     */
    @Resource
    private PersonService personService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Person selectOne(Integer id) {
        return this.personService.queryById(id);
    }
    @PostMapping("deleteOne")
    public boolean deleteOne(Integer id) {
        userService.hello();
         this.personService.deleteById(id);
         return this.personService.deleteById(id+1);
    }
    @PostMapping("insertOne")
    public Person updateOne(Person person) {
            return personService.insert(person);
    }


}