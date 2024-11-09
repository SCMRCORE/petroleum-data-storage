import WordPreviewer from "../DataManagement/components/WordPreviewer/index.tsx";

const DevTest = () => {
  return (
    <div>
      <div style={{ width: "100%", height: "70vh" }}>
        <WordPreviewer
          fileUrl={
            "http://web-core.oss-cn-beijing.aliyuncs.com/396c2df4-e35e-47aa-a68c-cd8e994acb28.doc"
          }
        />
      </div>
    </div>
  );
};

export default DevTest;
