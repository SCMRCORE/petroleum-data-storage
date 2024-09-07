import { Button } from "@arco-design/web-react"
import useStore from "../../store/index.js"
import SearchTable from "./components/SearchTable/index.js"
const Home = () => {
  const { count, inc } = useStore()

  return (
    <div>
      <span>{count}</span>
      <Button onClick={inc}>one up</Button>
      <SearchTable></SearchTable>
    </div>
  )
}

export default Home