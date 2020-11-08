package oit.is.z1227.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatches();

  @Select("SELECT ID, USER_1, USER_2, USER_1_HAND, USER_2_HAND, IS_ACTIVE FROM MATCHES WHERE USER_1=#{user_1} and USER_2=#{user_2};")
  ArrayList<Match> selectMatches(int user_1, int user_2);

  @Insert("INSERT INTO matches (user_1,user_2,user_1_hand,user_2_hand) VALUES (#{user_1},#{user_2},#{user_1_hand},#{user_2_hand});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);
}
