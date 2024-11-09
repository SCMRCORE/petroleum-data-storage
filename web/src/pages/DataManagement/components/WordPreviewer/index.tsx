import React, { useEffect, useRef } from "react";
// import { renderAsync } from "docx-preview";

interface WordPreviewerProps {
  fileUrl: string;
  onError?: (error: Error) => void;
}

const WordPreviewer: React.FC<WordPreviewerProps> = ({ fileUrl, onError }) => {
  const containerRef = useRef<HTMLDivElement>(null);
  //
  // useEffect(() => {
  //   const loadAndRenderDoc = async () => {
  //     try {
  //       // 获取文档内容
  //       const response = await fetch(fileUrl);
  //       const blob = await response.blob();
  //
  //       // 确保容器存在
  //       if (containerRef.current) {
  //         // 渲染文档
  //         await renderAsync(blob, containerRef.current, containerRef.current, {
  //           className: "docx-viewer",
  //           // inWrapper: true,
  //         });
  //       }
  //     } catch (error) {
  //       console.error("文档预览失败:", error);
  //       onError?.(error as Error);
  //     }
  //   };
  //
  //   loadAndRenderDoc();
  // }, [fileUrl, onError]);
  return (
    // <div style={{ width: "100%", height: "70vh" }}>
    <iframe
      style={{ width: "100%", height: "100%", border: "none" }}
      src={`https://view.officeapps.live.com/op/view.aspx?src=` + fileUrl}
    ></iframe>
    // </div>
  );
  // return <div className="word-previewer-container" ref={containerRef} />;
};

export default WordPreviewer;
