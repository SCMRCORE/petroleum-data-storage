import { createBrowserRouter } from "react-router-dom"
import Home from "../pages/Home/index.js"

const routerList = createBrowserRouter([
  {
    path: '/',
    element: <Home></Home>
  },
  {
    path: '/test',
    element: <div>test</div>
  },
])

export default routerList