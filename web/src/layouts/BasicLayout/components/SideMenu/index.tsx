import { Menu } from "@arco-design/web-react";
import { routeList } from "../../../../routers/index.tsx";

const MenuItem = Menu.Item;

const SideMenu = () => {
  const myNavigate = (item: { path: string }) => {
    location.hash = item.path;
  };

  return (
    <div>
      <Menu className="h-full shrink-0 bg-gray-200">
        {routeList.map((item) => (
          <MenuItem
            key={item.path}
            className="flex items-center"
            onClick={() => {
              myNavigate(item);
            }}
          >
            {item.name}
          </MenuItem>
        ))}
      </Menu>
    </div>
  );
};

export default SideMenu;
