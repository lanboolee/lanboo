package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lanboo
 * @date 2018/10/30 13:46
 */
public class ScmOrderUnitDetailDto extends ScmBase implements Serializable {

    private static final long serialVersionUID = 4291793715856350741L;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 到货日期
     */
    private Date arriveDate;
    /**
     * 订单编号
     */
    private String billNo;
    /**
     *
     */
    private Integer seriNo;
    /**
     * 档口
     */
    private String stallId;
    private String stallName;
    /**
     * 物品id
     */
    private String goodsId;
    /**
     * 物品名称
     */
    private String goodsName;
    /**
     * 物品规格
     */
    private String goodsSpec;
    /**
     * 物品编码
     */
    private String goodsCode;
    /**
     * 物品不含税金额
     */
    private Double goodsAmtNotax;
    /**
     * 物品数量
     */
    private Double goodsQty;
    /**
     * 含税金额
     */
    private Double goodsTaxAmt;

    /**
     * 验收物品数量
     */
    private Double checkGoodsQty;
    private Double checkGoodsAmt;
    /**
     * 差异
     */
    private Double diffGoodsQty;
    private Double diffGoodsAmt;
    /**
     * 标准单位
     */
    private String unitId;
    private String unitName;
    private Double unitPrice;
    /**
     * 标准单位不含税单价
     */
    private Double unitPriceNotax;

    /**
     * 采购单位
     */
    private String ordUnitId;
    private String ordUnitName;
    private Double ordUnitPrice;

    /**
     * 辅助单位
     */
    private String dualUnitId;
    private String dualUnitName;
    private Double dualGoodsQty;

    /**
     * 税率
     */
    private Double taxRatio;


    private String remarks;

    /**
     * 仓库
     */
    private String depotId;
    private String depotName;

}
