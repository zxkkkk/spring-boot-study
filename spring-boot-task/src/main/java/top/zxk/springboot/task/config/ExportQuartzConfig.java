package top.zxk.springboot.task.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zxk.springboot.task.jobs.ExportJob;


@Configuration
public class ExportQuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ExportJob.class)
                .withIdentity("exportTask")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");

        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("exportTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}