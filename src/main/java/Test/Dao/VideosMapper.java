package Test.Dao;

import Test.Entity.Videos;

public interface VideosMapper {
    int deleteByPrimaryKey(String id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKeyWithBLOBs(Videos record);

    int updateByPrimaryKey(Videos record);

    Videos findByName(String name);
}