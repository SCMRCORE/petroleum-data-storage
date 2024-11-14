import WordPreviewer from "../DataManagement/components/WordPreviewer/index.tsx";
import FileUploader from "../DataManagement/components/FileUploader/index.tsx";
const DevTest = () => {
  return (
    <div>
      这是开发测试页面，发布前记得在/web/routers/index.tsx把这个页面的路由注释掉，左侧按钮就会自动消失
      <h2>这是 doc 预览组件</h2>
      <div style={{ width: "100%", height: "600px" }}>
        <WordPreviewer
          fileUrl={
            "https://web-core.oss-cn-beijing.aliyuncs.com/396c2df4-e35e-47aa-a68c-cd8e994acb28.doc"
          }
        />
      </div>
      <h2>这是 doc 文件上传组件</h2>
      <div style={{ width: "100%", height: "600px" }}>
        <FileUploader onUploadSuccess={() => {}} onUploadError={() => {}} />
      </div>
    </div>
  );
};

export default DevTest;
