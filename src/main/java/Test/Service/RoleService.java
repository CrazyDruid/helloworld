package Test.Service;

import Test.Dao.RoleMapper;
import Test.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/6/14.
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role getId(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
