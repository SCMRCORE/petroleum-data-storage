import { Menu } from '@arco-design/web-react';
import { IconApps, IconSafe, IconBulb, IconRobot, IconFire } from '@arco-design/web-react/icon';
import 'tailwindcss/tailwind.css'; // 确保已经安装了Tailwind CSS并正确引入

const MenuItem = Menu.Item;
const SubMenu = Menu.SubMenu;

const SideMenu = () => {
  return (
    <div>
      <Menu className='h-full shrink-0 bg-gray-200'  >
        <MenuItem key='0' className='flex items-center'>
          <IconApps className='mr-2' />
          Navigation 1
        </MenuItem>
        <SubMenu
          key='1'
          title={
            <>
              <IconRobot className='mr-2' />
              Navigation 2
            </>
          }
        >
          <MenuItem key='1_0'>Beijing</MenuItem>
          <MenuItem key='1_1'>Shanghai</MenuItem>
          <MenuItem key='1_2'>Guangzhou</MenuItem>
        </SubMenu>
        <SubMenu
          key='2'
          title={
            <>
              <IconBulb className='mr-2' />
              Navigation 3
            </>
          }
        >
          <MenuItem key='2_0'>Wuhan</MenuItem>
          <MenuItem key='2_1'>Chengdu</MenuItem>
        </SubMenu>
        <MenuItem key='3' className='flex items-center'>
          <IconSafe className='mr-2' />
          Navigation 4
        </MenuItem>
        <MenuItem key='4' className='flex items-center'>
          <IconFire className='mr-2' />
          Navigation 5
        </MenuItem>
      </Menu>
    </div>
  );
};

export default SideMenu;