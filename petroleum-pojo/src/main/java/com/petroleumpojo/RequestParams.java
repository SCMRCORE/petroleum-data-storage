package com.petroleumpojo;
import lombok.Data;

@Data
public class RequestParams {
    private String token;
    private String appCode;
    private String apiToken;

    private Float SPR3;           // 累计钻头进尺
    private Float TVA;            // 钻井液池总体积
    private Float DATA3;          // 扩展参数3
    private String GUID;          // 自动编号
    private Float MTOA;           // 出口钻井液温度
    private String DATE_TIME_INDEX; // 时间索引
    private Float SPR5;           // 大钩速度
    private Float LSTK;           // 迟到泵冲数
    private String WID;           // 油井标识
    private Float DATA11;         // 扩展参数11
    private Float HKLA;           // 悬重
    private String collect_job_id; // 采集任务标识
    private Float WOBX;           // 钻压（最大值）
    private String MONTH;         // 产生记录时的月份
    private Float TQX;            // 扭矩（最大值）
    private Float DATA4;          // 扩展参数4
    private Float DATA15;         // 扩展参数15
    private Float MFIA;           // 入口流量
    private String DATE;          // 产生记录时的日期
    private Float SPR4;           // 瞬时机械钻速
    private Float STKC;           // 累计泵冲数
    private Float ROPA;           // 机械钻速
    private String is_submit_centre_db; // 是否提交中心库
    private String data_asset_code; // 数据资产编码
    private String delete_flag;   // 删除标识
    private String archive_flag;  // 归档标识
    private String dataset_class; // 数据集编码
    private String update_date;   // 最近更新时间
    private String update_user;   // 最近更新人
    private String update_org;    // 最近更新单位
    private String quality_tag;   // 质量标志
    private String branch_id;     // 所属分公司
    private String acqtn_mode;    // 数据采集方式
    private Float SPPA;           // 泵压
    private String verify_date;   // 审核时间
    private String verifier;      // 审核人
    private String verify_org;    // 审核单位
    private String secrecy_level; // 密级
    private String input_time;    // 录入时间
    private String input_user;    // 录入人
    private String input_org;     // 录入单位
    private String uniontime;     // 汇集时间
    private String publish_org;   // 发布单位
    private Float GASA;           // 气全量
    private Float DATA10;         // 扩展参数10
    private Float SPM3;           // 3号泵冲数
    private Float DRTM;           // 迟到深度
    private Float DBTV;           // 钻头垂深
    private String SKNO;          // 侧钻/井段号
    private Float TVCA;           // 钻井液净体积变化
    private Float SPR1;           // 累计纯钻进时间
    private Float MFOP;           // 返出流量比
    private Float MDOA;           // 出口密度
    private Float CHKP;           // 套压
    private Float MTIA;           // 入口钻井液温度
    private Float TQA;            // 扭矩
    private Float HKLX;           // 悬重（最大值）
    private Float MCIA;           // 入口钻井液电导率
    private Float DATA14;         // 扩展参数14
    private Float DATA9;          // 扩展参数9
    private Float DATA1;          // 扩展参数1
    private Float SPM1;           // 1号泵冲数
    private String SEQID;         // 序列标识
    private String ACTC;          // 作业代码
    private Long TIME;            // 产生记录时的时间
    private Float SPM4;           // 4号泵冲数
    private Float DMEA;           // 测量井深
    private Float SPM2;           // 2号泵冲数
    private Float DATA13;         // 扩展参数13
    private Float SPR2;           // 迟到时间
    private Float WOBA;           // 钻压
    private Float DVER;           // 垂直井深
    private Float MDIA;           // 入口密度
    private Float DATA12;         // 扩展参数12
    private Float DATA8;          // 扩展参数8
    private Short RID;            // 记录标识
    private Float DATA6;          // 扩展参数6
    private Float DATA2;          // 扩展参数2
    private Float MFOA;           // 返出流量
    private Float BPOS;           // 大钩高度
    private String ACTC2;         // 作业状态
    private Float RPMA;           // 转速
    private Float DATA5;          // 扩展参数5
    private Float DBTM;           // 钻头测量深度
    private Float DATA7;          // 扩展参数7
    private Float MCOA;           // 出口钻井液电导率
}
