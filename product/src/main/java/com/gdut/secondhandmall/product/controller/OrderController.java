package com.gdut.secondhandmall.product.controller;

import com.gdut.secondhandmall.common.result.CommonResult;
import com.gdut.secondhandmall.product.dto.PageParamDTO;
import com.gdut.secondhandmall.product.dto.VerificationDTOForOrder;
import com.gdut.secondhandmall.product.model.OmsOrderCompletedDO;
import com.gdut.secondhandmall.product.model.OmsOrderOngoingDO;
import com.gdut.secondhandmall.product.model.OmsOrderReturnApplyDO;
import com.gdut.secondhandmall.product.service.OmsOrderCompletedService;
import com.gdut.secondhandmall.product.service.OmsOrderOngoingService;
import com.gdut.secondhandmall.product.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GGXian
 * @project secondhandmall
 * @createTime 2020/8/16-19:03
 * @description
 **/
@Api(tags = "订单模块")
@RestController
@RequestMapping("/orderService")
public class OrderController {
    @Autowired
    OmsOrderOngoingService orderOngoingService;
    @Autowired
    OmsOrderCompletedService orderCompletedService;
    @Autowired
    OmsOrderReturnApplyService orderReturnApplyService;

    /**
     * 获取全部未完成订单
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取全部未完成订单", httpMethod = "GET")
    @GetMapping("/orderOnGoing")
    public CommonResult<List<OmsOrderOngoingDO>> getAllOngoingOrders(@RequestBody PageParamDTO pageParam){
        List<OmsOrderOngoingDO> allUnfinishedOrders = orderOngoingService.getAllUnfinishedOrders(pageParam);
        if (allUnfinishedOrders == null){
            return CommonResult.failed();
        }
        return CommonResult.success(allUnfinishedOrders);
    }

    /**
     * 获取某用户的未收货订单
     * @param sessionKey
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取某用户的未收货订单", httpMethod = "GET")
    @GetMapping("/buyerOrderOnGoing/{sessionKey}")
    public CommonResult<List<OmsOrderOngoingDO>> getOngoingOrderForBuyer(@PathVariable String sessionKey,
                                                                         @RequestBody PageParamDTO pageParam){
        List<OmsOrderOngoingDO> allUnfinishedOrdersForBuyer = orderOngoingService.getAllUnfinishedOrdersForBuyer(pageParam, sessionKey);
        if (allUnfinishedOrdersForBuyer == null){
            return CommonResult.failed();
        }
        return CommonResult.success(allUnfinishedOrdersForBuyer);
    }

    /**
     * 获取某用户的未发货订单
     * @param sessionKey
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取某用户的未发货订单", httpMethod = "GET")
    @GetMapping("/sellerOrderOnGoing/{sessionKey}")
    public CommonResult<List<OmsOrderOngoingDO>> getOngoingOrderForSeller(@PathVariable String sessionKey,
                                                                         @RequestBody PageParamDTO pageParam){
        List<OmsOrderOngoingDO> allUnfinishedOrdersForseller = orderOngoingService.getAllUnfinishedOrdersForseller(pageParam, sessionKey);
        if (allUnfinishedOrdersForseller == null){
            return CommonResult.failed();
        }
        return CommonResult.success(allUnfinishedOrdersForseller);
    }

    /**
     * 获取所有已完成订单
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取所有已完成订单", httpMethod = "GET")
    @GetMapping("/orderCompleted")
    public CommonResult<List<OmsOrderCompletedDO>> getAllOnCompletedOrders(@RequestBody PageParamDTO pageParam){
        List<OmsOrderCompletedDO> allOrder = orderCompletedService.getAllOrder(pageParam);
        if (allOrder == null){
            return CommonResult.failed();
        }
        return CommonResult.success(allOrder);
    }

    /**
     * 获取某用户的已发货订单
     * @param sessionKey
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取某用户的已发货订单", httpMethod = "GET")
    @GetMapping("/sellerOrderCompleted/{sessionKey}")
    public CommonResult<List<OmsOrderCompletedDO>> getCompletedOrderForSeller(@PathVariable String sessionKey,
                                                                              @RequestBody PageParamDTO pageParam){
        List<OmsOrderCompletedDO> orderForSeller = orderCompletedService.getOrderForSeller(pageParam, sessionKey);
        if (orderForSeller == null){
            return CommonResult.failed();
        }
        return CommonResult.success(orderForSeller);
    }

    /**
     * 获取某用户的已收货订单
     * @param sessionKey
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取某用户的已收货订单", httpMethod = "GET")
    @GetMapping("/buyerOrderCompleted/{sessionKey}")
    public CommonResult<List<OmsOrderCompletedDO>> getCompletedOrderForBuyer(@PathVariable String sessionKey,
                                                                              @RequestBody PageParamDTO pageParam){
        List<OmsOrderCompletedDO> orderForBuyer = orderCompletedService.getOrderForBuyer(pageParam, sessionKey);
        if (orderForBuyer == null){
            return CommonResult.failed();
        }
        return CommonResult.success(orderForBuyer);
    }

    /**
     * 获取所有的退单审核记录
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取所有的退单审核记录", httpMethod = "GET")
    @GetMapping("/orderReturnApply")
    public CommonResult<List<OmsOrderReturnApplyDO>> getAllOrderReturnApply(@RequestBody PageParamDTO pageParam){
        List<OmsOrderReturnApplyDO> all = orderReturnApplyService.getAll(pageParam);
        if (all == null){
            return CommonResult.failed();
        }
        return CommonResult.success(all);
    }

    /**
     * 获取某用户的退单审核记录
     * @param sessionKey
     * @param pageParam
     * @return
     */
    @ApiOperation(value = "获取某用户的退单审核记录", httpMethod = "GET")
    @GetMapping("/orderReturnApply/{sessionKey}")
    public CommonResult<List<OmsOrderReturnApplyDO>> getOrderReturnApplyForBuyer(@PathVariable String sessionKey,
                                                    @RequestBody PageParamDTO pageParam){
        List<OmsOrderReturnApplyDO> orderForBuyer = orderReturnApplyService.getOrderForBuyer(pageParam, sessionKey);
        if (orderForBuyer == null){
            return CommonResult.failed();
        }
        return CommonResult.success(orderForBuyer);
    }

    /**
     * 提交退货请求
     * @param orderId
     * @param reason
     * @return
     */
    @ApiOperation(value = "提交退货请求", httpMethod = "POST")
    @PostMapping("/newReturnApply/{orderId}")
    public CommonResult postNewReturnApply(@PathVariable long orderId, @RequestBody OmsOrderReturnApplyDO reason){
        if(!orderReturnApplyService.putNewApply(orderId, reason.getReason())){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    /**
     * 提交新订单
     * @param
     * @param
     * @return
     */
    @ApiOperation(value = "提交新订单", httpMethod = "POST")
    @PostMapping("/newOrder")
    public CommonResult postNewOrder(@RequestBody OmsOrderOngoingDO order){
        String sessionKey = order.getBuyerId();
        long productId = order.getProductId();
        System.out.println(sessionKey + "+" + productId);
        if (!orderOngoingService.postNewOrder(sessionKey, productId)){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    /**
     * 确认交易完成
     * @param orderId
     * @return
     */
    @ApiOperation(value = "确认交易完成", httpMethod = "PUT")
    @PutMapping("/tradeConfirmation/{orderId}")
    public CommonResult tradeConfirmation(@PathVariable long orderId){
        System.out.println("orderid:" + orderId);
        if (!orderCompletedService.confirmCompletion(orderId)){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    /**
     * 管理员审核退货请求
     * @param orderId
     * @param verification
     * @return
     */
    @ApiOperation(value = "管理员审核退货请求", httpMethod = "POST")
    @PostMapping("/applyVerification/{orderId}")
    public CommonResult applyVerification(@PathVariable long orderId, @RequestBody VerificationDTOForOrder verification){
        verification.setOrderId(orderId);
        if (!orderReturnApplyService.completeVerification(verification)){
            return CommonResult.failed();
        }
        return CommonResult.success();
    }
}
