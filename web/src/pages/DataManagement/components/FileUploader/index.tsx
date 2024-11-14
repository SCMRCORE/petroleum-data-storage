import React, { useState } from "react";
import { Upload, Modal, Button, Input, Alert } from "@arco-design/web-react";
import { IconUpload, IconEye, IconDelete } from "@arco-design/web-react/icon";
import { uploadWordFile } from "../../../../services/searchTable.ts";

interface FileUploaderProps {
  onUploadSuccess?: (fileInfo: Record<string, unknown>) => void;
  onUploadError?: (error: Error) => void;
}

interface UploadedFile {
  name: string;
  size: number;
  url: string;
  status?: "success" | "error" | "loading"; // Added loading status
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
    if (file.url) {
      window.open(file.url); // Use the OSS URL for preview
    }
  };

  const beforeUpload = (file: File, filesList: File[]) => {
    if (!wellName.trim()) {
      showAlert("上传失败，请先输入关联井名", "error");
      return false;
    }
    return true;
  };

  const handleUpload = async () => {
    console.log("文件列表", uploadedFileList);
    for (const file of uploadedFileList) {
      try {
        setUploadedFileList([...uploadedFileList]); // 重新渲染状态
        const response = await uploadWordFile(file, wellName); // Call the upload function
        if (response?.data?.code === 1) {
          file.status = "success"; // Mark as success
          file.url = response.data.url; // Get the OSS URL
          showAlert(`文件 ${file.name} 上传成功`, "success");
        } else {
          file.status = "error"; // Mark as error
          showAlert(
            `文件 ${file.name} 上传失败: ${response.data.message}`,
            "error"
          );
        }
        file.status = "loading";
      } catch (error) {
        file.status = "error";
        showAlert(`文件 ${file.name} 上传失败: ${error}`, "error");
      }
    }
    setUploadedFileList([...uploadedFileList]);
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
        // icon={<IconUpload />}
        className="w-[80%]"
      >
        上传文件
      </Button>

      <Modal
        title="上传文档"
        visible={modalVisible}
        onCancel={() => setModalVisible(false)}
        footer={null}
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
            renderUploadList={(filesList, props) => {
              return (
                <div style={{ maxHeight: "200px", overflow: "auto" }}>
                  {filesList.map((file) => {
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
                        {file.status === "loading" && (
                          <span style={{ color: "orange" }}>上传中...</span>
                        )}
                        {file.status === "success" && (
                          <span style={{ color: "green" }}>上传成功</span>
                        )}
                        {file.status === "error" && (
                          <span style={{ color: "red" }}>
                            上传失败,请删除重试上传
                          </span>
                        )}
                        {file.status === "success" && (
                          <Button
                            type="text"
                            icon={<IconEye />}
                            onClick={() => handlePreviewFile(file)}
                            style={{ marginRight: "10px" }}
                          />
                        )}
                        <Button
                          type="text"
                          icon={<IconDelete />}
                          onClick={() => handleRemoveFile(file, props)}
                          style={{ marginRight: "10px" }}
                        />
                      </div>
                    );
                  })}
                </div>
              );
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
          <div style={{ display: "flex", justifyContent: "center" }}>
            <Button
              type="primary"
              style={{ width: "80%", marginTop: 16 }}
              onClick={handleUpload}
            >
              确认上传
            </Button>
          </div>
        </div>
      </Modal>
    </>
  );
};

export default FileUploader;
