// import React, { useEffect, useRef } from "react";

// interface WordPreviewerProps {
//   fileUrl: string;
//   onError?: (error: Error) => void;
// }

const WordPreviewer = ({ fileUrl }) => {
  return (
    <iframe
      style={{ width: "100%", height: "100%", border: "none" }}
      src={`https://view.officeapps.live.com/op/view.aspx?src=https://web-core.oss-cn-beijing.aliyuncs.com/396c2df4-e35e-47aa-a68c-cd8e994acb28.doc` + fileUrl}
    ></iframe>
  );
};

export default WordPreviewer;
