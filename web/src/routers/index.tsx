import { createHashRouter } from "react-router-dom";
import DataManagement from "../pages/DataManagement/index.js";
import DevTest from "../pages/DevTest/index.tsx";

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
    name: "数据管理",
    path: "/",
    element: <DataManagement></DataManagement>,
  },
  // {
  //   name: "数据趋势",
  //   path: "/trend",
  //   element: <DataTrend></DataTrend>,
  // },
  ...extraConfig,
];

const routerList = createHashRouter(routeList);

export default routerList;
