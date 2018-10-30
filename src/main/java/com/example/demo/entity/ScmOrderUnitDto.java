package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lanboo
 * @date 2018/10/30 13:19
 */
public class ScmOrderUnitDto extends ScmBase implements Serializable {

    private static final long serialVersionUID = 640548750175833982L;

    /**
     * 订单id
     */
    private String id;
    /**
     * 订单类型(三种)1.总部采购 2.直运采购 3.门店自采
     */
    private Integer orderType;
    /**
     * 订单编号,有三种订单
     */
    private String billNo;
    /**
     * 请购日期
     */
    private Date bussDate;
    /**
     * 仓库id
     */
    private String depotId;
    /**
     * 仓库名称
     */
    private String depotName;
    /**
     * 供应商id
     */
    private String supplierId;
    /**
     * 供应商名字
     */
    private String supplierName;
    /**
     * 含税总金额
     */
    private Double totalAmt;
    /**
     * 不含税总金额
     */
    private Double totalAmtNotax;
    /**
     * 状态码  订单状态
     */
    private Integer orderStatus;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 订单类型 人工采购956 智能采购957
     */
    private String billType;
    /**
     * 验收时间
     */
    private Date checkTime;
    /**
     * 验收总金额
     */
    private Double checkTotalAmt;
    private Double checkTotalAmtNoTax;
    /**
     * 机构id
     */
    private String orgInfoId;
    /**
     * 机构名称
     */
    private String orgInfoName;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 到货日期
     */
    private Date arrivalDate;
    /**
     * 抹零总金额
     */
    private Double totalAmtModify;
    /**
     * 抹零状态 0没有抹零  1已经抹零
     */
    private Integer modifyPriceStatus;
    /**
     * 已关单的上一个状态
     */
    private Integer preStatus;
    /**
     * 0 手动关单 1自动关单
     */
    private Integer closeOrderType;
    /**
     * 驳回原因
     */
    private String rejectReason;
    /**
     * 作废原因
     */
    private String cancelReason;
    /**
     * 档口
     */
    private String stallId;
    private String stallName;
    /**
     * 子表DTO(物品相关)
     */
    List<ScmOrderUnitDetailDto> detailDtoList;
}
