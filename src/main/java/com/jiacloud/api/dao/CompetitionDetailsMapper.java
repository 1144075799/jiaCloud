package com.jiacloud.api.dao;

import com.jiacloud.api.domain.CompetitionDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompetitionDetailsMapper {

    /**参加竞赛**/
    CompetitionDetails joinCompetitionDetails(@Param(value = "captain") String captain,@Param(value = "member") String member,@Param(value = "number") String number,@Param(value = "telephone") String telephone,@Param(value = "QQ") String QQ,@Param(value = "className") String className,@Param(value = "teamName") String teamName,@Param(value = "remark") String remark,@Param(value = "competitionName") String competitionName);

    /**返回竞赛参加人员信息**/
    List<CompetitionDetails> findCompetitionDetails(@Param(value = "competitionName") String competitionName);


}
