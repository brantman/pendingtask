package com.leading.pendingtask.service;

import com.leading.pendingtask.dto.PendingTaskInfoDTO;

import java.util.List;

/**
 * @Description:
 * @Author yinshihao
 * @Date 2019/6/13 17:13
 */
public interface PendingTaskService {
    /**
     * 首页待处理
     *
     * @param userId
     * @return
     */
    List<PendingTaskInfoDTO> getPendingTaskInfoByCopyOnWriteArrayList(Long userId);

    List<PendingTaskInfoDTO> getPendingTaskInfoByMultipleArrayList(Long userId);

    List<PendingTaskInfoDTO> getPendingTaskInfoBySynchronizedList(Long userId);
}
