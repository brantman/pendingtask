package com.leading.pendingtask.service;

import com.leading.pendingtask.dto.PendingTaskGenerator;
import com.leading.pendingtask.dto.PendingTaskInfoDTO;
import com.leading.thread.LeadingFeignRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author yinshihao
 * @Date 2019/6/13 17:14
 */
@Service
@Slf4j
public class PendingTaskServiceImpl implements PendingTaskService {

    @Autowired
    private LeadingFeignRunnable leadingFeignRunnable;

    @Override
    public List<PendingTaskInfoDTO> getPendingTaskInfoByCopyOnWriteArrayList(Long userId) {
        log.debug("start getPendingTaskInfoByCopyOnWriteArrayList ...");
        Instant start = Instant.now();

        List<PendingTaskInfoDTO> dtoList = new CopyOnWriteArrayList<>();
        int pendingTaskTypeCount = 6;

        CountDownLatch countDownLatch = new CountDownLatch(pendingTaskTypeCount);

        for (int i = 0; i < pendingTaskTypeCount; i++) {
            int taskCount = i + 1;
            leadingFeignRunnable.run(() -> {
                try {
                    dtoList.addAll(PendingTaskGenerator.getPendingTasks(taskCount));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("###### 任务列表获取失败", e);
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.debug("finish getPendingTaskInfoByCopyOnWriteArrayList with {} ms", timeElapsed);

        return dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<PendingTaskInfoDTO> getPendingTaskInfoByMultipleArrayList(Long userId) {
        log.debug("start getPendingTaskInfoByMultipleArrayList ...");
        Instant start = Instant.now();

        int pendingTaskTypeCount = 6;
        List<List<PendingTaskInfoDTO>> taskLists = new ArrayList<List<PendingTaskInfoDTO>>(pendingTaskTypeCount);
        for (int i = 0; i < pendingTaskTypeCount; i++) {
            taskLists.add(new ArrayList<PendingTaskInfoDTO>());
        }

        CountDownLatch countDownLatch = new CountDownLatch(pendingTaskTypeCount);

        for (int i = 0; i < pendingTaskTypeCount; i++) {
            int index = i;
            int taskCount = index + 1;
            leadingFeignRunnable.run(() -> {
                try {
                    taskLists.get(index).addAll(PendingTaskGenerator.getPendingTasks(taskCount));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("###### 任务列表获取失败", e);
        }

        List<PendingTaskInfoDTO> dtoList = new ArrayList<>();
        for (List taskList : taskLists) {
            dtoList.addAll(taskList);
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.debug("finish getPendingTaskInfoByMultipleArrayList with {} ms", timeElapsed);

        return dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<PendingTaskInfoDTO> getPendingTaskInfoBySynchronizedList(Long userId) {
        log.debug("start getPendingTaskInfoBySynchronizedList ...");
        Instant start = Instant.now();

        List<PendingTaskInfoDTO> dtoList = Collections.synchronizedList(new ArrayList<PendingTaskInfoDTO>());
        int pendingTaskTypeCount = 6;

        CountDownLatch countDownLatch = new CountDownLatch(pendingTaskTypeCount);

        for (int i = 0; i < pendingTaskTypeCount; i++) {
            int taskCount = i + 1;
            leadingFeignRunnable.run(() -> {
                try {
                    dtoList.addAll(PendingTaskGenerator.getPendingTasks(taskCount));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("###### 任务列表获取失败", e);
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.debug("finish getPendingTaskInfoBySynchronizedList with {} ms", timeElapsed);

        return dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
