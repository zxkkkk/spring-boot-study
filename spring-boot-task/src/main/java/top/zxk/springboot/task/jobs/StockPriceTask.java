package top.zxk.springboot.task.jobs;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.zxk.springboot.task.entity.StockPrice;
import top.zxk.springboot.task.mapper.StockPriceMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

//@Component
@AllArgsConstructor
public class StockPriceTask {
    private static final Logger log = LoggerFactory.getLogger(StockPriceTask.class);
    private final StockPriceMapper stockPriceMapper;

    private final Random random = new Random();

    @Scheduled(fixedRate = 10000)
    public void updateStockPrice() {
        double price = 100 + random.nextDouble() * 50;

        // 使用 BigDecimal 保留两位小数
        BigDecimal roundedPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);

        StockPrice stockPrice = new StockPrice();
        stockPrice.setPrice(roundedPrice.doubleValue()); // 如果 StockPrice 的 price 属性是 double 类型
        stockPrice.setName("小米");
        stockPrice.setUpdateTime(LocalDateTime.now());

        stockPriceMapper.insert(stockPrice);
        log.info("股票价格已经更新：{}, 时间：{}", roundedPrice, LocalDateTime.now());
    }
}
