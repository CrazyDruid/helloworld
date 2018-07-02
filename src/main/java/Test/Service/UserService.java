package Test.Service;

import KeyPrefix.RedisKeyPrefix;
import Test.Dao.UserMapper;
import Test.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
public class UserService{

    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String,User> redisTemplate;

    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    public User getUser(String id) {
        String key = RedisKeyPrefix.USER + id;
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            User user = redisTemplate.opsForValue().get(key);
//            User user = (User) RedisUtil.get(key);
            System.out.println("从缓存中获取了用户！");
            return user;
        }
        // 从数据库取，并存回缓存
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null) return null;
        // 放入缓存，并设置缓存时间
        redisTemplate.opsForValue().set(key,user, 600,TimeUnit.SECONDS);
        System.out.println("存入缓存!");
        return user;
    }

    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
        String userId = user.getId();
        // 缓存存在，删除缓存
        String key = RedisKeyPrefix.USER + userId;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            System.out.println("删除缓存!");
        }
    }

    public void deleteById(String id) {
        userMapper.deleteByPrimaryKey(id);
        // 缓存存在，删除缓存
        String key = RedisKeyPrefix.USER + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            System.out.println("删除用户!");
        }
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
//    public List<User> findAllUser(int pageNum, int pageSize) {
//        //将参数传给这个方法就可以实现物理分页了，非常简单。
//        PageHelper.startPage(pageNum, pageSize);
//        return userMapper.selectAllUser();
//    }
}
