package com.leading.pendingtask;

import com.leading.pendingtask.service.PendingTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PendingtaskApplicationTests {

	@Autowired
	PendingTaskService pendingTaskService;

	@Test
	void getPendingTaskInfoByCopyOnWriteArrayList() {
		//Given

		//when
		pendingTaskService.getPendingTaskInfoByCopyOnWriteArrayList(1L);

		//then
	}

	@Test
	void getPendingTaskInfoByMultipleArrayList() {
		//Given

		//when
		pendingTaskService.getPendingTaskInfoByMultipleArrayList(1L);

		//then
	}

	@Test
	void getPendingTaskInfoBySynchronizedList() {
		//Given

		//when
		pendingTaskService.getPendingTaskInfoBySynchronizedList(1L);

		//then
	}

}
