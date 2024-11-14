import {Menu} from "@arco-design/web-react";
import routerList, {routeList} from "../../routers/index.js";
import {useState} from "react";
const MenuItem = Menu.Item;

const DataLakes = () => {
    const [index, setIndex] = useState(0)
    const myNavigate = (id) => {
        // location.hash = routerList[1].path + item.path;
        setIndex(id)
    };

    return (
        <div>
            <Menu className="flex h-full shrink-0 bg-gray-200">
                {routeList[1].childrenList.map((item, id) => (
                    <div >
                        <MenuItem
                            key={item.path}
                            className="flex items-center"
                            onClick={() => {
                                myNavigate(id);
                            }}
                        >
                            {item.name}
                        </MenuItem>
                        {
                            index === id ? <div>
                                {item.element}
                            </div> : null
                        }
                    </div>
                ))}
            </Menu>
        </div>
    );
};

export default DataLakes