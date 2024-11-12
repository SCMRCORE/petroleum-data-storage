import { createHashRouter } from "react-router-dom";
import DataManagement from "../pages/DataManagement/index.js";
import DevTest from "../pages/DevTest/index.tsx";
import DataLake from "../pages/DataLake/index.tsx";
import FileManagement from "../pages/FileManagement/index.tsx";

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
    name: "数据湖数据",
    path: "/data-lake",
    element: <DataLake />,
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
