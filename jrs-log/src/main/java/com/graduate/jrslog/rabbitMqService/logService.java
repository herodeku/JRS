package com.graduate.jrslog.rabbitMqService;

import com.graduate.jrslog.util.AnalyzeUtil;
import com.graduate.jrslog.util.RabbitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class logService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitUtil rabbitUtil;
    @Scheduled(cron = "0 0 6 * * *")//每天6点刷新
    public void analyzeLog() throws IOException {
        List<List<String>> lists = AnalyzeUtil.analyzeLog();
        logger.info("analyzeLog",lists);
        rabbitUtil.populaceEnqueue(lists.get(0));
        rabbitUtil.judicialOfficerEnqueue(lists.get(1));
    }
}
