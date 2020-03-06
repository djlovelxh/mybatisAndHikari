package dj.mybatis.dao;

import dj.mybatis.comfig.DataSource;
import dj.mybatis.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by dj on 2020/2/22.
 */
public interface IUserDao {
     List<User> findAll() ;

     @DataSource
     List<Map<String, Object>> getName();
}
