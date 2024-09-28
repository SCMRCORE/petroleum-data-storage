import { createHashRouter } from "react-router-dom";
import DataManagement from "../pages/DataManagement/index.js";

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
];

const routerList = createHashRouter(routeList);

export default routerList;
