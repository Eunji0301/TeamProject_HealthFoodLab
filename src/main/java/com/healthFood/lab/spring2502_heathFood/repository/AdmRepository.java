package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdmRepository {
    //@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
    public void doJoin(String uEmail, String uPwd, String uName,
                       String uPhone, String uBirth, String uNickName,
                       String uGender, String uTOSAgree, String uPIPAgree, String uIP, String uThumbnail, String uRole);

    @Select("SELECT * FROM User WHERE uIdx = #{uIdx}")
    public User getUserById(int uIdx);

    @Select("SELECT * FROM User WHERE uEmail=#{uEmail} ")
    public User getUserByEmail(String uEmail) ;


}


