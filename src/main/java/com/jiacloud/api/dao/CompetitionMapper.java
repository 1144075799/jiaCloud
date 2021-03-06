package com.jiacloud.api.dao;

import com.jiacloud.api.domain.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CompetitionMapper {

        /**发布竞赛**/
        Competition addCompetition(@Param(value = "name") String name,@Param(value = "time") String time,@Param(value = "deadline") String deadline,@Param(value = "grade") String grade,@Param(value = "sponsor") String sponsor,@Param(value = "presentation") String presentation);

        /**查找竞赛**/
        Competition findCompetition(@Param(value = "name") String name);

        /**
         * 返回所有的竞赛
         * @return
         */
        List<Competition> findAllCompetition();

        /**
         * 查找到期时间
         * @param name
         * @return
         */
        Competition findCompetitionDeadline(@Param(value = "name") String name);

        /**
         * 添加文件
         * @param DocName
         * @param name
         * @return
         */
        String addDocName(@Param(value = "DocName") String DocName,@Param(value = "name") String name);

        /**
         * 添加图片
         * @param imgPath
         * @param name
         * @return
         */
        String addImagePath(@Param(value = "imgPath") String imgPath,@Param(value = "name") String name);

        /**
         * 结束竞赛
         * @param name
         * @return
         */
        Competition deleteCompetition(@Param(value = "name") String name);

        /**
         * 删除竞赛相关人员
         * @param name
         * @return
         */
        Competition deleteCompetitionMember(@Param(value = "name") String name);
}
