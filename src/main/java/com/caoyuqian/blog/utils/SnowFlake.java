package com.caoyuqian.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qian
 * @version V1.0
 * @Title: SnowFlake
 * @Package: com.caoyuqian.blog.utils
 * @Description: 雪花算法生成id
 * @date 2018/8/16 上午9:20
 **/
public class SnowFlake {

    private Logger log= LoggerFactory.getLogger(this.getClass());
    // 开始时间截 (从2015-01-01起)
    private static final long START_TIME = 1420041600000L;
    // 机器ID所占位数
    private static final long ID_BITS = 5L;
    // 数据中心ID所占位数
    private static final long DATA_CENTER_ID_BITS = 5L;
    // 机器ID最大值31 (此移位算法可很快计算出n位二进制数所能表示的最大十进制数)
    private static final long MAX_ID = ~(-1L << ID_BITS);
    // 数据中心ID最大值31
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    // Sequence所占位数
    private static final long SEQUENCE_BITS = 12L;
    // 机器ID偏移量12
    private static final long ID_SHIFT_BITS = SEQUENCE_BITS;
    // 数据中心ID偏移量12+5=17
    private static final long DATA_CENTER_ID_SHIFT_BITS = SEQUENCE_BITS + ID_BITS;
    // 时间戳的偏移量12+5+5=22
    private static final long TIMESTAMP_LEFT_SHIFT_BITS =
            SEQUENCE_BITS + ID_BITS + DATA_CENTER_ID_BITS;
    // Sequence掩码4095
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    // 上一毫秒数
    private static long lastTimestamp = -1L;
    // 毫秒内Sequence(0~4095)
    private static long sequence = 0L;
    // 机器ID(0-31)
    private final long workerId;
    // 数据中心ID(0-31)
    private final long dataCenterId;

    /**
     * 构造
     *
     * @param workerId 机器ID(0-31)
     * @param dataCenterId 数据中心ID(0-31)
     */
    public SnowFlake(long workerId, long dataCenterId) {
        if (workerId > MAX_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format(
                            "datacenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        log.info(
                String.format(
                        "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                        TIMESTAMP_LEFT_SHIFT_BITS, DATA_CENTER_ID_BITS, ID_BITS, SEQUENCE_BITS, workerId));
    }

    /**
     * 生成ID（线程安全）
     *
     * @return id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟被修改过，回退在上一次ID生成时间之前应当抛出异常！！！
        if (timestamp < lastTimestamp) {
            log.error(
                    String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new IllegalStateException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内sequence生成
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 溢出处理
            if (sequence == 0) { // 阻塞到下一毫秒,获得新时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { // 时间戳改变，毫秒内sequence重置
            sequence = 0L;
        }
        // 上次生成ID时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算组成64位ID
        return ((timestamp - START_TIME) << TIMESTAMP_LEFT_SHIFT_BITS)
                | (dataCenterId << DATA_CENTER_ID_SHIFT_BITS)
                | (workerId << ID_SHIFT_BITS)
                | sequence;
    }

    /**
     * 阻塞到下一毫秒,获得新时间戳
     *
     * @param lastTimestamp 上次生成ID时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
