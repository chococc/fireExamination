package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.FireOrder;

/**
 *
 * 订阅之后 状态 为1  订阅
 * 2.支付
 * 3.开始考试
 * 4.结束考试
 * 5.评卷
 *
 */
public interface StudentPayService {
    /**
     * 订阅
     * @param examineId
     */
    Results<FireOrder> orderExamine(Integer examineId) throws InterruptedException;

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    Results<FireOrder> payExamine(Integer orderId);

    /**
     *
     * @param orderId
     * @return
     */
    Results<FireOrder> startExamine(Integer orderId);

    /**
     * 结束考试
     * @param orderId
     * @return
     */
    Results<FireOrder> finishExamine(Integer orderId);

    /**
     * 评卷
     * @param orderId
     * @return
     */
    Results<FireOrder> viewExamine(Integer orderId);
}
