package com.petroleumcommom.constant;

/**
 * 一些常量字符串整合
 */
public final class Const {
    //JWT令牌
    public final static String JWT_BLACK_LIST = "jwt:blacklist:";
    public final static String JWT_FREQUENCY = "jwt:frequency:";
    //请求频率限制
    public final static String FLOW_LIMIT_COUNTER = "flow:counter:";
    public final static String FLOW_LIMIT_BLOCK = "flow:block:";
    //邮件验证码
    public final static String VERIFY_EMAIL_LIMIT = "verify:email:limit:";
    public final static String VERIFY_EMAIL_DATA = "verify:email:data:";
    //过滤器优先级
    public final static int ORDER_FLOW_LIMIT = -101;
    public final static int ORDER_CORS = -102;
    //请求自定义属性
    public final static String ATTR_USER_ID = "userId";
    //消息队列
    public final static String MQ_MAIL = "mail";
    //用户角色
    public final static String ROLE_DEFAULT = "user";
    //论坛相关计数限制
    public final static String FORUM_IMAGE_COUNTER = "image:upload:";
    public final static String FORUM_WEATHER_CACHE = "weather:cache:";
    public final static String FORUM_TOPIC_CREATE_COUNTER = "topic:create:";
    public final static String FORUM_TOPIC_COMMENT_COUNTER = "topic:comment:";
    public final static String FORUM_TOPIC_PREVIEW_CACHE = "topic:preview:";

    // 获取appCode值
    public final static String DATALAKE_GET_APPCODE_URL = "http://datalake.cnooc/apigateway/appauth/getappid";
    // 基本信息表
    public final static String DATALAKE_GET_TABLE_1 = "https://datalake.cnooc/apigateway/rgodt/DW_BJ_DWDzdysjjcxfw_4286";
    // 测斜
    public final static String DATALAKE_GET_TABLE_2 = "https://datalake.cnooc/apigateway/ypjnk/DW_BJ_DWDzdysjjcxfw_4462";
    // 钻头
    public final static String DATALAKE_GET_TABLE_3 = "https://datalake.cnooc/apigateway/djexp/DW_BJ_DWDzdysjjcxfw_9565";
    // 钻遇地层
    public final static String DATALAKE_GET_TABLE_4 = "https://datalake.cnooc/apigateway/dgxhl/DW_BJ_DWDzdysjjcxfw_6967";
    // 钻井液性能
    public final static String DATALAKE_GET_TABLE_5 = "https://datalake.cnooc/apigateway/gzdgu/DW_BJ_DWDzdysjjcxfw_4153";
    // 钻井时间
    public final static String DATALAKE_GET_TABLE_6 = "https://datalake.cnooc/apigateway/nnxpp/DW_BJ_DWDzdysjjcxfw_1395";
    // 钻井深度
    public final static String DATALAKE_GET_TABLE_7 = "https://datalake.cnooc/apigateway/dntfk/DW_BJ_DWDzdysjjcxfw_1835";
    public final static String REDIS_KEY_APPCODE = "appcode";

    // 各个表对应的apiToken
    public final static String DATALAKE_APITOKEN_1 = "JVGZIQZLKBEUEGMF";
    public final static String DATALAKE_APITOKEN_2 = "OMRWGAWNUQBCJNUV";
    public final static String DATALAKE_APITOKEN_3 = "GCMNBSCGOMWEEMMB";
    public final static String DATALAKE_APITOKEN_4 = "RAWOAEQJVXONMEVU";
    public final static String DATALAKE_APITOKEN_5 = "JOMSCIMZAKHRJDEI";
    public final static String DATALAKE_APITOKEN_6 = "QBNKDIVDHALWFMCF";
    public final static String DATALAKE_APITOKEN_7 = "FXPIKHLPQPWTEAVM";
















}