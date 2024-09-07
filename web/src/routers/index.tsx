import { createHashRouter } from "react-router-dom";
import Home from "../pages/Home/index.js";

const routeList = [
  {
    path: "/",
    element: <Home></Home>,
  },
  {
    path: "/test",
    element: <div>test</div>,
  },
];

const routerList = createHashRouter(routeList);

export default routerList;
