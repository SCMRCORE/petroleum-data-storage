import React from 'react';
import { RouterProvider } from 'react-router-dom';
import router from '../../routers/index.js';
import ContentWrapper from "./components/ContentWrapper/index.js";
import SideMenu from "./components/SideMenu/index.js";

interface BasicLayoutProps extends React.HTMLAttributes<HTMLDivElement> {
  children: React.ReactNode;
}

const BasicLayout: React.FC<BasicLayoutProps> = ({ children, ...rest }) => {
  return (
    <div
      {...rest}
      className="h-screen w-screen flex gap-4 overflow-hidden will-change-auto"
    >
      <SideMenu />
      <ContentWrapper className="flex-1">
        <RouterProvider router={router} />
      </ContentWrapper>
    </div>
  );
};

export default BasicLayout;