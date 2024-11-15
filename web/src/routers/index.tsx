import { createHashRouter } from "react-router-dom";
import DataManagement from "../pages/DataManagement/index.js";
import DevTest from "../pages/DevTest/index.tsx";
import DataLake from "../pages/DataLake/index.tsx";
import FileManagement from "../pages/FileManagement/index.tsx";
import DataLake2 from "../pages/DataLake2/index.js";
import DataLake7 from "../pages/DataLake7/index.js";
import React from "react";
import DataLake6 from "../pages/DataLake6/index.js";
import DataLake5 from "../pages/DataLake5/index.js";
import DataLake4 from "../pages/DataLake4/index.js";
import DataLake3 from "../pages/DataLake3/index.js";
import DataLakes from "../pages/DataLakes/index.js";

const isDebug = true;
const extraConfig = isDebug
  ? [
      {
        name: "开发测试",
        path: "/dev-test",
        element: <DevTest></DevTest>,
      },
    ]
  : [];

export const routeList = [
  {
    name: "本地数据",
    path: "/",
    element: <DataManagement />,
  },
  {
    name: "DataLakes",
    path: "/datalakes",
    element: <DataLakes />,
    childrenList: [
      {
        name: "作业井基本信息",
        path: "/data-lake-1",
        element: <DataLake />,
      },
      {
        name: "测斜数据",
        path: "/data-lake-2",
        element: <DataLake2 />,
      },
      {
        name: "钻头数据",
        path: "/data-lake-3",
        element: <DataLake3 />,
      },
      {
        name: "钻遇地层",
        path: "/data-lake-4",
        element: <DataLake4 />,
      },
      {
        name: "钻井液性能",
        path: "/data-lake-5",
        element: <DataLake5 />,
      },
      {
        name: "钻井时间数据",
        path: "/data-lake-6",
        element: <DataLake6 />,
      },
      {
        name: "钻井深度数据",
        path: "/data-lake-7",
        element: <DataLake7 />
      },
    ],

  },
  {
    name: "文件管理",
    path: "/file-management",
    element: <FileManagement />,
  },
  ...extraConfig,
];

const routerList = createHashRouter(routeList);

export default routerList;
