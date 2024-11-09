import React, { useCallback, useState } from "react";
import { Upload, Message } from "@arco-design/web-react";
import { IconUpload } from "@arco-design/web-react/icon";
import type { UploadProps } from "@arco-design/web-react";

interface FileUploaderProps {
  acceptedFileTypes?: string[];
  maxSize?: number;
  chunkSize?: number;
  onUploadSuccess?: (fileInfo: Record<string, unknown>) => void;
  onUploadError?: (error: Error) => void;
}

const FileUploader: React.FC<FileUploaderProps> = ({
  acceptedFileTypes = [".doc", ".docx"],
  maxSize = 100,
  chunkSize = 5,
  onUploadSuccess,
  onUploadError,
}) => {
  const [uploading, setUploading] = useState(false);

  // 创建文件切片
  const createFileChunks = (file: File, chunkSize: number) => {
    const chunks = [];
    const maxChunkSize = chunkSize * 1024 * 1024; // 转换为字节
    const fileSize = file.size;
    let start = 0;

    while (start < fileSize) {
      const end = Math.min(start + maxChunkSize, fileSize);
      const chunk = file.slice(start, end);
      chunks.push(chunk);
      start = end;
    }

    return chunks;
  };

  // 上传文件切片
  const uploadChunks = async (chunks: Blob[], file: File) => {
    try {
      const uploadPromises = chunks.map((chunk, index) => {
        const formData = new FormData();
        formData.append("chunk", chunk);
        formData.append("chunkIndex", index.toString());
        formData.append("fileName", file.name);
        formData.append("totalChunks", chunks.length.toString());

        // TODO: 替换为实际的上传 API
        return fetch("/api/upload/chunk", {
          method: "POST",
          body: formData,
        });
      });

      await Promise.all(uploadPromises);

      // TODO: 调用合并切片的 API
      await fetch("/api/upload/merge", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          fileName: file.name,
          totalChunks: chunks.length,
        }),
      });

      onUploadSuccess?.({ fileName: file.name, size: file.size });
    } catch (error) {
      onUploadError?.(error);
      throw error;
    }
  };

  const handleUpload = useCallback(
    async (file: File) => {
      // 检查文件类型
      const fileType = "." + file.name.split(".").pop()?.toLowerCase();
      if (!acceptedFileTypes.includes(fileType)) {
        Message.error(`只支持 ${acceptedFileTypes.join(", ")} 格式的文件`);
        return false;
      }

      // 检查文件大小
      const fileSizeMB = file.size / (1024 * 1024);
      if (fileSizeMB > maxSize) {
        Message.warning(`文件大小超过 ${maxSize}MB，将进行切片上传`);
      }

      try {
        setUploading(true);

        if (fileSizeMB > maxSize) {
          // 大文件切片上传
          const chunks = createFileChunks(file, chunkSize);
          await uploadChunks(chunks, file);
        } else {
          // 小文件直接上传
          const formData = new FormData();
          formData.append("file", file);

          // TODO: 替换为实际的上传 API
          await fetch("/api/upload", {
            method: "POST",
            body: formData,
          });

          onUploadSuccess?.({ fileName: file.name, size: file.size });
        }

        Message.success("上传成功！");
      } catch (error) {
        Message.error("上传失败，请重试！");
        onUploadError?.(error);
      } finally {
        setUploading(false);
      }

      return false; // 阻止默认上传行为
    },
    [acceptedFileTypes, maxSize, chunkSize, onUploadSuccess, onUploadError]
  );

  const uploadProps: UploadProps = {
    accept: acceptedFileTypes.join(","),
    multiple: false,
    beforeUpload: handleUpload,
    showUploadList: false,
    disabled: uploading,
    drag: true,
  };

  return (
    <Upload {...uploadProps}>
      <div className="arco-upload-drag-box">
        <div className="arco-upload-drag-icon">
          <IconUpload />
        </div>
        <div className="arco-upload-drag-text">点击或拖拽文件到此区域上传</div>
        <div className="arco-upload-drag-description">
          支持 {acceptedFileTypes.join(", ")} 格式的文件，
          {maxSize}MB 以上的文件将自动进行切片上传
        </div>
        {uploading && <div>上传中...</div>}
      </div>
    </Upload>
  );
};

export default FileUploader;
