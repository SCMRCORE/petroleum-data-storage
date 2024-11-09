import WordPreviewer from "../DataManagement/components/WordPreviewer/index.tsx";

const DevTest = () => {
  return (
    <div>
      123
      <WordPreviewer
        fileUrl={
          "http://web-core.oss-cn-beijing.aliyuncs.com/396c2df4-e35e-47aa-a68c-cd8e994acb28.doc"
        }
      />
    </div>
  );
};

export default DevTest;
