package dj.mybatis.service.impl;

import dj.mybatis.entity.Person;
import dj.mybatis.dao.PersonDao;
import dj.mybatis.service.PersonService;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Person)表服务实现类
 *
 * @author makejava
 * @since 2020-03-04 14:21:47
 */
//@Service("personService")
@Service
@Transactional(propagation=Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonDao personDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(readOnly = true) //查询设置为只读事务：这样不加锁
    public Person queryById(Integer id) {
        return this.personDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @Transactional(readOnly = true) //查询设置为只读事务：这样不加锁
    public List<Person> queryAllByLimit(int offset, int limit) {
        return this.personDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param person 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Person insert(Person person) {
        this.personDao.insert(person);
        person.setName("433");
        int i = 1/0;  // 抛出异常
        personDao.update(person);
        return person;
    }

    /**
     * 修改数据
     *
     * @param person 实例对象
     * @return 实例对象
     */
    @Override
    public Person update(Person person) {
//        PersonService personService = (PersonService) AopContext.currentProxy();
        personDao.update(person);
        person.setName("433336666666666666666666565");
//        personService.update(person);
        personDao.update(person);
//        return this.queryById(person.getId());
        return this.queryById(1);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
//    @Async
    public boolean deleteById(Integer id) {
        return this.personDao.deleteById(id) > 0;
    }
}