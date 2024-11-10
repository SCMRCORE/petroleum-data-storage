import React, { useState } from "react";
import {
  Upload,
  Modal,
  Button,
  Input,
  Alert,
  Grid,
} from "@arco-design/web-react";
import { IconUpload, IconEye, IconDelete } from "@arco-design/web-react/icon";

const { Row, Col } = Grid;

interface FileUploaderProps {
  onUploadSuccess?: (fileInfo: Record<string, unknown>) => void;
  onUploadError?: (error: Error) => void;
}

interface UploadedFile {
  name: string;
  size: number;
  url: string;
}

const FileUploader: React.FC<FileUploaderProps> = ({}) => {
  const [modalVisible, setModalVisible] = useState(false);
  const [uploadedFileList, setUploadedFileList] = useState<UploadedFile[]>([]);
  const [wellName, setWellName] = useState("");
  const [alertVisible, setAlertVisible] = useState(false);
  const [alertContent, setAlertContent] = useState("");
  const [alertType, setAlertType] = useState<
    "success" | "error" | "info" | "warning"
  >("info");

  const showAlert = (
    content: string,
    type: "success" | "error" | "info" | "warning"
  ) => {
    setAlertContent(content);
    setAlertType(type);
    setAlertVisible(true);
    setTimeout(() => setAlertVisible(false), 3000); // Auto-hide after 3 seconds
  };

  const isAcceptFile = (file, accept) => {
    if (accept && file) {
      const accepts = Array.isArray(accept)
        ? accept
        : accept
            .split(",")
            .map((x) => x.trim())
            .filter((x) => x);
      const fileExtension =
        file.name.indexOf(".") > -1 ? file.name.split(".").pop() : "";
      return accepts.some((type) => {
        const text = type && type.toLowerCase();
        const fileType = (file.type || "").toLowerCase();
        if (text === fileType) {
          return true;
        }
        if (new RegExp("/*").test(text)) {
          const regExp = new RegExp("/.*$");
          return fileType.replace(regExp, "") === text.replace(regExp, "");
        }
        if (new RegExp("..*").test(text)) {
          return text === `.${fileExtension && fileExtension.toLowerCase()}`;
        }
        return false;
      });
    }
    return !!file;
  };

  const handleRemoveFile = (file, props) => {
    props.onRemove(file);
  };

  const handlePreviewFile = (file) => {
    const fileURL = URL.createObjectURL(file.originFile);
    window.open(`https://view.officeapps.live.com/op/view.aspx?src=${fileURL}`);
  };

  const beforeUpload = (file: File, filesList: File[]) => {
    if (!wellName.trim()) {
      showAlert("上传失败，请先输入关联井名", "error");
      return false;
    }
    return true;
  };

  const handleSuccess = (response: any, file: any) => {
    showAlert(`文件 ${file.name} 上传成功`, "success");
  };

  const handleError = (error: any, file: any) => {
    showAlert(
      `文件 ${file.name} 上传失败: ${error.message || "未知错误"}`,
      "error"
    );
  };

  return (
    <>
      {alertVisible && (
        <div
          style={{
            position: "fixed",
            top: 20,
            right: "50%",
            zIndex: 10000,
            transform: "translateX(50%)",
          }}
        >
          <Alert
            style={{ marginBottom: 20 }}
            type={alertType}
            content={alertContent}
          />
        </div>
      )}
      <Button
        type="primary"
        onClick={() => setModalVisible(true)}
        icon={<IconUpload />}
      >
        上传文档
      </Button>

      <Modal
        title="上传文档"
        visible={modalVisible}
        onCancel={() => setModalVisible(false)}
        footer={
          <Button type="primary" onClick={() => setModalVisible(false)}>
            关闭
          </Button>
        }
        style={{ width: 600 }}
      >
        <div style={{ marginBottom: 20 }}>
          <Input
            style={{ marginBottom: 16 }}
            placeholder="请输入关联井名"
            value={wellName}
            onChange={setWellName}
            label="关联井名"
          />
          <Upload
            drag
            multiple
            accept=".doc,.docx"
            action="/"
            fileList={uploadedFileList}
            onChange={(fileList) => setUploadedFileList(fileList)}
            beforeUpload={beforeUpload}
            onSuccess={handleSuccess}
            onError={handleError}
            renderUploadList={(filesList, props) => {
              return filesList.map((file) => {
                return (
                  <div
                    style={{
                      display: "flex",
                      alignItems: "center",
                      marginBottom: "10px",
                      padding: "10px",
                      border: "1px solid #e0e0e0",
                      borderRadius: "5px",
                    }}
                  >
                    <span style={{ flexGrow: 1 }}>{file.name}</span>
                    <Button
                      type="text"
                      icon={<IconEye />}
                      onClick={() => handlePreviewFile(file)}
                      style={{ marginRight: "10px" }}
                    />
                    <Button
                      type="text"
                      icon={<IconDelete />}
                      onClick={() => handleRemoveFile(file, props)}
                      style={{ marginRight: "10px" }}
                    />
                  </div>
                );
              });
            }}
            onDrop={(e) => {
              const uploadFile = e.dataTransfer.files[0];
              if (!isAcceptFile(uploadFile, ".doc,.docx")) {
                showAlert(
                  "只支持 doc 和 docx 两种文件，请重新上传指定文件类型",
                  "info"
                );
              }
            }}
            tip="支持 doc 和 docx 两种文件上传"
          />
        </div>
      </Modal>
    </>
  );
};

export default FileUploader;
