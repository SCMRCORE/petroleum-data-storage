import React from 'react';

interface ContentWrapperProps extends React.HTMLAttributes<HTMLDivElement> {
  children: React.ReactNode;
}

const ContentWrapper: React.FC<ContentWrapperProps> = ({ children, ...rest }) => {
  return (
    <div
      {...rest}
      className="w-full bg-white rounded-lg p-6"
    >
      {children}
    </div>
  );
};

export default ContentWrapper;