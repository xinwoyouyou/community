package life.majiang.community.mapper;

import com.sun.tracing.ProbeName;
import life.majiang.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 作者:悠悠我心
 */
@Mapper
public interface UserMapper {
    @Insert("insert into USER(NAME,ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED)\n" +
            "values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);

    List<User> queryAll();

    //@Select("select * from community.user where token=#{token}")
    User finByToken(@Param("token") String token);
}
