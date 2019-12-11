package com.leading.pendingtask.dto;

import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author junwang
 * @date 2019/12/10
 */
@Slf4j
public class PendingTaskGenerator {
    public static List<PendingTaskInfoDTO> getPendingTasks(int taskCount) {
        EasyRandom generator = new EasyRandom();
        int size = new Random().nextInt(100 - 10) + 10;
        List<PendingTaskInfoDTO> pendingTaskList = generator.objects(PendingTaskInfoDTO.class, taskCount)
                .collect(Collectors.toList());
        log.debug(pendingTaskList.toString());
        return pendingTaskList;
    }
}
