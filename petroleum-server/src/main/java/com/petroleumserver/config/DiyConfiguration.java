package com.petroleumserver.config;

import com.petroleumcommom.constant.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Objects;

/**
 * 实现自己定义一个diy的configuration，返回自己定义的bean
 */
@Configuration
public class DiyConfiguration {

    /**
     *
     * 作为返回数据湖访问接口map
     * @return
     */
    @Bean
    public HashMap<Integer, String> generateDataLakeMap() {
        HashMap<Integer, String> datalakeMap = new HashMap<>();
        datalakeMap.put(1, Const.DATALAKE_GET_TABLE_1);
        datalakeMap.put(2, Const.DATALAKE_GET_TABLE_2);
        datalakeMap.put(3, Const.DATALAKE_GET_TABLE_3);
        datalakeMap.put(4, Const.DATALAKE_GET_TABLE_4);
        datalakeMap.put(5, Const.DATALAKE_GET_TABLE_5);
        datalakeMap.put(6, Const.DATALAKE_GET_TABLE_6);
        datalakeMap.put(7, Const.DATALAKE_GET_TABLE_7);
        return datalakeMap;
    }

    /**
     * 获取每一个接口所对应的链接的apiToken
     * @return
     */
    @Bean
    public HashMap<Integer, Object> getApiTokenMap() {
        HashMap<Integer, Object> apiTokenMap = new HashMap<>();
        apiTokenMap.put(1, Const.DATALAKE_APITOKEN_1);
        apiTokenMap.put(2, Const.DATALAKE_APITOKEN_2);
        apiTokenMap.put(3, Const.DATALAKE_APITOKEN_3);
        apiTokenMap.put(4, Const.DATALAKE_APITOKEN_4);
        apiTokenMap.put(5, Const.DATALAKE_APITOKEN_5);
        apiTokenMap.put(6, Const.DATALAKE_APITOKEN_6);
        apiTokenMap.put(7, Const.DATALAKE_APITOKEN_7);
        return apiTokenMap;
    }

    /**
     * 返回logCurveInfo 和 mnemonic uid 映射关系
     */
    @Bean
    public HashMap<String, String> uidToMnemonicMap() {
        HashMap<String, String> uidToMnemonicMap = new HashMap<>();
        uidToMnemonicMap.put("lic_1", "dTim");  // 时间曲线（DateTime）
        uidToMnemonicMap.put("lic_2", "md");    // 测量深度（Measured Depth）
        uidToMnemonicMap.put("lic_3", "MCOA");  // 电导率（Measured Conductivity）
        uidToMnemonicMap.put("lic_4", "MDIA");  // 密度（Density）
        uidToMnemonicMap.put("lic_5", "MTOA");  // 温度（Temperature）
        uidToMnemonicMap.put("lic_6", "MFIA");  // 流量（Flow rate）
        uidToMnemonicMap.put("lic_7", "MDOA");  // 密度（Density）
        uidToMnemonicMap.put("lic_8", "LSTK");  // 标定值（Unitless）
        uidToMnemonicMap.put("lic_9", "DRTM");  // 深度（Depth）
        uidToMnemonicMap.put("lic_10", "HKLA"); // 重量（Weight）
        uidToMnemonicMap.put("lic_11", "WOBA"); // 重量（Weight）
        uidToMnemonicMap.put("lic_12", "DMEA"); // 深度（Depth）
        uidToMnemonicMap.put("lic_13", "DVER"); // 深度（Depth）
        uidToMnemonicMap.put("lic_14", "BPOS"); // 位置（Position）
        uidToMnemonicMap.put("lic_15", "ROPA"); // 钻进速率（Rate of penetration）
        uidToMnemonicMap.put("lic_16", "DBTV"); // 深度（Depth）
        uidToMnemonicMap.put("lic_17", "TQA");  // 扭矩（Torque）
        uidToMnemonicMap.put("lic_18", "TVCA"); // 体积（Volume）
        uidToMnemonicMap.put("lic_19", "TVA");  // 体积（Volume）
        uidToMnemonicMap.put("lic_20", "SPM3"); // 特定速率（SPM Rate）
        uidToMnemonicMap.put("lic_21", "SPM2"); // 特定速率（SPM Rate）
        uidToMnemonicMap.put("lic_22", "SPM1"); // 特定速率（SPM Rate）
        uidToMnemonicMap.put("lic_23", "CHKP"); // 压强（Pressure）
        uidToMnemonicMap.put("lic_24", "SPPA"); // 压强（Pressure）
        uidToMnemonicMap.put("lic_25", "RPMA"); // 转速（RPM）
        uidToMnemonicMap.put("lic_26", "MFOA"); // 流量（Flow rate）
        uidToMnemonicMap.put("lic_27", "MFOP"); // 流量（Flow rate）
        uidToMnemonicMap.put("lic_28", "DBTM"); // 深度（Depth）
        uidToMnemonicMap.put("lic_29", "BitTim"); // 钻头时间（Bit time）
        uidToMnemonicMap.put("lic_30", "GASA"); // 气体含量（Gas content）
        uidToMnemonicMap.put("lic_31", "BitRun"); // 钻进运行（Bit run）
        uidToMnemonicMap.put("lic_32", "LAGtim"); // 滞后时间（Lag time）
        uidToMnemonicMap.put("lic_33", "ROP");   // 钻进速率（Rate of penetration）
        uidToMnemonicMap.put("lic_34", "HKS");   // 钻头速率（Head Speed）
        uidToMnemonicMap.put("lic_35", "MCIA");  // 电导率（Conductivity）
        uidToMnemonicMap.put("lic_36", "STKC");  // 无单位（Unitless）
        uidToMnemonicMap.put("lic_37", "MTIA");  // 温度（Temperature）
        return uidToMnemonicMap;
    }

}
