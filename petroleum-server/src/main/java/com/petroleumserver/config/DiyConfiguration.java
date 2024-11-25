package com.petroleumserver.config;

import com.petroleumcommom.constant.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Objects;

 
@Configuration
public class DiyConfiguration {

     
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

     
    @Bean
    public HashMap<String, String> uidToMnemonicMap() {
        HashMap<String, String> uidToMnemonicMap = new HashMap<>();
        uidToMnemonicMap.put("lic_1", "dTim");  
        uidToMnemonicMap.put("lic_2", "md");    
        uidToMnemonicMap.put("lic_3", "MCOA");  
        uidToMnemonicMap.put("lic_4", "MDIA");  
        uidToMnemonicMap.put("lic_5", "MTOA");  
        uidToMnemonicMap.put("lic_6", "MFIA");  
        uidToMnemonicMap.put("lic_7", "MDOA");  
        uidToMnemonicMap.put("lic_8", "LSTK");  
        uidToMnemonicMap.put("lic_9", "DRTM");  
        uidToMnemonicMap.put("lic_10", "HKLA"); 
        uidToMnemonicMap.put("lic_11", "WOBA"); 
        uidToMnemonicMap.put("lic_12", "DMEA"); 
        uidToMnemonicMap.put("lic_13", "DVER"); 
        uidToMnemonicMap.put("lic_14", "BPOS"); 
        uidToMnemonicMap.put("lic_15", "ROPA"); 
        uidToMnemonicMap.put("lic_16", "DBTV"); 
        uidToMnemonicMap.put("lic_17", "TQA");  
        uidToMnemonicMap.put("lic_18", "TVCA"); 
        uidToMnemonicMap.put("lic_19", "TVA");  
        uidToMnemonicMap.put("lic_20", "SPM3"); 
        uidToMnemonicMap.put("lic_21", "SPM2"); 
        uidToMnemonicMap.put("lic_22", "SPM1"); 
        uidToMnemonicMap.put("lic_23", "CHKP"); 
        uidToMnemonicMap.put("lic_24", "SPPA"); 
        uidToMnemonicMap.put("lic_25", "RPMA"); 
        uidToMnemonicMap.put("lic_26", "MFOA"); 
        uidToMnemonicMap.put("lic_27", "MFOP"); 
        uidToMnemonicMap.put("lic_28", "DBTM"); 
        uidToMnemonicMap.put("lic_29", "BitTim"); 
        uidToMnemonicMap.put("lic_30", "GASA"); 
        uidToMnemonicMap.put("lic_31", "BitRun"); 
        uidToMnemonicMap.put("lic_32", "LAGtim"); 
        uidToMnemonicMap.put("lic_33", "ROP");   
        uidToMnemonicMap.put("lic_34", "HKS");   
        uidToMnemonicMap.put("lic_35", "MCIA");  
        uidToMnemonicMap.put("lic_36", "STKC");  
        uidToMnemonicMap.put("lic_37", "MTIA");  
        return uidToMnemonicMap;
    }

}
