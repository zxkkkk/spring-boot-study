package top.zxk.springboot.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j

public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log")
    public String logDemo() {
        logger.trace("This is a trace log.");
        logger.debug("This is a debug log.");
        logger.info("This is an info log.");
        logger.warn("This is a warning log.");
        logger.error("This is an error log.");
        return "Logs have been generated    ";
    }
}
