package com.sakura.study.dao;

import com.sakura.study.dto.OperationLogDto;
import com.sakura.study.model.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperationLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);

    /**
     * 获取分页的操作信息
     * @param employeeId
     * @param start
     * @param pageCount
     * @return
     */
    List<OperationLogDto> getPageLogs(@Param("employeeId") Integer employeeId, @Param("start")Integer start,
                                      @Param("pageCount")Integer pageCount);

    /**
     * 获取操作信息数量
     * @param employeeId
     * @return
     */
    int getLogsCount(Integer employeeId);
}