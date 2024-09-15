const FZ =
{
  "wellName": "DF1-1-P12H",
  "company": "海南分公司",
  "primaryWellType": "调整井",
  "wellType": "大位移井",
  "startTime": [
    2022,
    4,
    14,
    0,
    45
  ],
  "endTime": [
    2022,
    4,
    14,
    3,
    45
  ],
  "durationHours": "3.00",
  "firstLevelCode": "D",
  "secondLevelCode": "N",
  "thirdLevelCode": "X",
  "fourthLevelCode": "01",
  "complexityType": "复杂情况",
  "wellSection": "一开",
  "startDepth": "1341.53",
  "endDepth": "1341.53",
  "operationDescription": "下钻到底。\r\n*500m浅层测试工具，参数：3300L/min，600psi，正常；\r\n*每500m打通一次。",
  "status": 1,
  "onlyKey": 660
};


const ZT =
{
  "wellName": "NB19-6-A19",
  "company": "海南分公司",
  "primaryWellType": "调整井",
  "wellType": "大位移井",
  "drillBitNumber": "7",
  "drillBitType": "PDC钻头",
  "sizeIn": "8 3/8",
  "manufacturer": "百施特",
  "model": "8.375″TS616",
  "serialNumber": "265653",
  "nozzleSize": "16/16/16/16/16/16",
  "drillBitTFAn2": "1.18",
  "entryDepth": "4083",
  "exitDepth": "5150",
  "entryTime": [
    2024,
    3,
    31,
    14,
    0
  ],
  "exitTime": [
    2024,
    4,
    5,
    14,
    30
  ],
  "advanceDistance": "1067.00",
  "pureDrillingTime": "74.19",
  "rop": "14.38",
  "maxDrillingPressure": "14.00",
  "minDrillingPressure": "12.00",
  "maxRotationSpeed": "120.00",
  "minRotationSpeed": "120.00",
  "iadcWearEvaluation": "1-1-WT-A-X-I-NO-TD",
  "encounteredFormation": "平湖组，龙井组，宝石组，花港组",
  "status": 1,
  "onlyKey": 335
};


const JS =
{
  "company": "海南分公司",
  "status": 1,
  "startTime": [
    2011,
    6,
    13,
    0,
    0
  ],
  "endTime": [
    2011,
    6,
    17,
    15,
    30
  ],
  "onlyKey": 1624,
  "primaryWellType": "调整井",
  "wellType": "大位移井",
  "wellName": "YC13-1-A16",
  "holeDiameter": "8 1/2",
  "endDepth": "6348.00",
  "steelGrade": "1Cr-L80",
  "yieldValue": "3-4.5",
  "startDepth": "6161.00",
  "wellSection": "四开",
  "casingLength": "",
  "casingDiameter": "",
  "casingSetDepth": "",
  "upperCasingDiameter": "9 5/8",
  "startVerticalDepth": "3735.03",
  "cementReturnHeight": "",
  "plasticViscosity": "6-6.5",
  "drillingFluidDensity": "0.95-0.96",
  "upperCasingSetDepth": "6153.71",
  "drillingFluidSystem": "油基钻井液",
  "endVerticalDepth": "3823.78",
  "formationPressure": "梅山组,三亚组"
};


const JB = {
  "wellName": "DF1-1-P8H",
  "company": "海南分公司",
  "oilFieldName": "东方1-1气田",
  "oilFieldCode": "IN20020100",
  "isCooperative": "自营",
  "contractor": "中海油田服务公司",
  "primaryWellType": "调整井",
  "secondaryWellType": "采气井",
  "tertiaryWellType": "",
  "wellType": "大位移井",
  "waterDepth1": "69.9",
  "designDepth": "3884",
  "designVerticalDepth": "1261.37",
  "designDate": "",
  "structureName": "莺歌海盆地中央泥底辟构造带西北部",
  "uniqueWellId": "ZJ2824",
  "chineseWellName": "东方1-1-P8H井",
  "operator": "湛江分公司",
  "hydrocarbonType": "",
  "riskLevel": "",
  "riskType": "常规",
  "riskGrade": "",
  "sulfurGrade": "不含硫",
  "containsCO2": "false",
  "zeroDepth": "转盘面",
  "initialKellyElevation": "40",
  "groundElevation": "",
  "currentKellyElevation": "",
  "casingHeadElevation": "",
  "waterDepth2": "24.75",
  "mudLineElevation": "-69.9",
  "artificialBottomDepth": "",
  "drillingDays": "",
  "positioningMethod": "DGPS",
  "location": "南海北部大陆架西区的莺歌海盆地",
  "geodeticSystem": "WGS84",
  "longitude": "107° 45' 37.414\" E",
  "latitude": "18° 36' 57.222\" N",
  "xcoordinate": "158109.12",
  "ycoordinate": "2061413.94",
  "slotNumber": "4",
  "country": "中国",
  "region": "南海西部",
  "province": "",
  "county": "",
  "scrapedWell": "false",
  "officePhone": "",
  "remark": "",
  "status": 1,
  "onlyKey": 36
};

const getKeys = (obj) => {
  const keys = Object.keys(obj);
  console.log('key set', keys);
  return keys;
};

const keys1 = getKeys(FZ);
const keys2 = getKeys(ZT);
const keys3 = getKeys(JS);
const keys4 = getKeys(JB);

const allKeys = [...keys1, ...keys2, ...keys3, ...keys4];
console.log('All Array', Array.from(new Set(allKeys)))

