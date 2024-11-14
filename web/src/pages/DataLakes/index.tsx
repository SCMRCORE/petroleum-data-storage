import { Menu, Tag } from "@arco-design/web-react";
import { routeList } from "../../routers/index.js";
import { useState } from "react";
const MenuItem = Menu.Item;

const DataLakes = () => {
  const [index, setIndex] = useState(0);
  const myNavigate = (id) => {
    setIndex(id);
  };

  return (
    <div>
      <div className="flex gap-1 cursor-pointer ">
        {routeList[1].childrenList.map((item, id) => (
          <Tag
            style={id === index ? { background: "skyblue" } : null}
            key={item.path}
            size="large"
            // className="w-[200px]"
            onClick={() => {
              myNavigate(id);
            }}
          >
            {item.name}
          </Tag>
        ))}
      </div>
      <div className="mt-4">{routeList[1].childrenList[index].element}</div>
    </div>
  );
};

export default DataLakes;
