package com.leading.thread;

import com.leading.pendingtask.dto.PendingTaskInfoDTO;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author junwang
 * @date 2019/12/10
 */
public class GetPendingTaskThread extends Thread {

    private List<PendingTaskInfoDTO> pendingTasks;

    public List<PendingTaskInfoDTO> getPendingTasks() {
        return pendingTasks;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (true) {
            System.out.println(this.getName() + ": New Thread is running..." + i++);
            try {
                EasyRandom generator = new EasyRandom();
                int size = new Random().nextInt(100 - 10) + 10;
                pendingTasks = generator.objects(PendingTaskInfoDTO.class, 10)
                        .collect(Collectors.toList());
                //Wait for one sec so it doesn't print too fast
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
