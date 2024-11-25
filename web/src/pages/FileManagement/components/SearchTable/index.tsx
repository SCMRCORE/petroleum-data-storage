import {
  Form,
  Grid,
  Input,
  Button,
  Table,
  Pagination,
  Modal,
  Tag,
  Message,
  
} from "@arco-design/web-react";
import {
  add,
  deleteItem,
  fileSearch,
  search,
  updateItem,
} from "../../../../services/searchTable.ts";
import { useEffect, useMemo, useState } from "react";
import {
  getColumns,
  formConfigList,
  EN_2_CN_TABLES,
  TableMode,
} from "./configs.tsx";
import { MixedItem } from "../../../../types/index.ts";
import * as XLSX from "xlsx";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  DATA_SOURCE_TABLE_TITLE_MAP,
  formatCnToEn,
} from "../../../../utils/checkDataSource.ts";
import FileUploader from "../../../DataManagement/components/FileUploader/index.tsx";
import WordPreviewer from "../../../DataManagement/components/WordPreviewer/index.tsx"; 
const pageSize = 10; 

type DataSourceType = Record<
  string,
  { list: Array<Partial<MixedItem>>; total: number }
>;

const SearchTable = () => {
   
  const [form] = Form.useForm();
   
  const [editForm] = Form.useForm();
   
  const [isModalVisible, setIsModalVisible] = useState(false);
   
  const [uploadFileInfoList, setUploadFileInfoList] = useState([]);
   
  const [uploadFileNameList, setUploadFileNameList] = useState([]);
   
  const [dataSource, setDataSource] = useState<{
    total: number;
    list: Array<any>;
  }>({
    total: 0,
    list: [],
  });
   
  const [pageIndex, setPageIndex] = useState<number>(1);
   
  const [isSearching, setIsSearching] = useState(false);
   
  const [isDeleting, setIsDeleting] = useState(false);
   
  const [isEditing, setIsEditing] = useState(false);
   
  const [editingData, setEditingData] = useState<Partial<MixedItem>>();
  const [activeFileUrl, setActiveFileUrl] = useState<string>("");
   
  
  
  

   
  const [selectedRowKeys, setSelectedRowKeys] = useState<Array<string>>([]);
   
  const [activeTab, setActiveTab] = useState<TableMode>(TableMode.CASE1);

   
  const handleSearch = async () => {
    try {
      setIsSearching(true);
      const formData = form.getFieldsValue();
      const res = await fileSearch({
        ...formData,
        pageIndex,
        pageSize,
      });

      console.log("请求的数据", res);
      
      setSelectedRowKeys([]);
      setIsSearching(false);
      setDataSource(res);
    } catch (err) {
      console.log("数据获取异常:", err);
    } finally {
      setIsSearching(false);
    }
  };

   
  const handleReset = () => {
    
    form.resetFields();
  };

  const batchDelete = async () => {
    setIsDeleting(true);
    const promiseList = selectedRowKeys.map((onlyKey) => {
      return deleteItem({
        OnlyKey: +onlyKey,
        num: activeTab,
      });
    });

    try {
      await Promise.all(promiseList);
      handleSearch();
    } catch (err) {
      console.error("删除失败：", err);
    } finally {
      setIsDeleting(false);
    }
  };

  const handlePageChange = (index) => {
    setPageIndex(index);
  };

  const handleView = (fileUrl) => {
    setActiveFileUrl(fileUrl);
    setIsModalVisible(true);
  };

  useEffect(() => {
    handleSearch();
  }, [pageIndex]);

  const columnsSet = useMemo(
    () => getColumns(handleSearch, handleView),
    
    []
  );

  return (
    <div>
      <Modal
        className="h-screen w-screen"
        visible={!!activeFileUrl}
        onCancel={() => setActiveFileUrl("")}
      >
        <div className="h-[85vh] w-full">
          <WordPreviewer fileUrl={activeFileUrl}></WordPreviewer>
        </div>
      </Modal>
      { }
      <Form form={form} id="searchForm" layout="vertical">
        <div className="flex w-[100%] ">
          <div className="w-[78%] mr-[12px]">
            {formConfigList.map((rowItemList, index) => {
              return (
                <Grid.Row gutter={24} key={index}>
                  {rowItemList.map((item) => {
                    return (
                      <Grid.Col span={8} key={item.field}>
                        <Form.Item
                          label={item.label + `(${item.field})`}
                          field={item.field}
                          initialValue={item.defaultValue}
                        >
                          <Input />
                        </Form.Item>
                      </Grid.Col>
                    );
                  })}
                </Grid.Row>
              );
            })}
          </div>
          <div className="w-[12%] flex flex-col items-center gap-[12px] border-l-2">
            <Button
              htmlType="submit"
              type="primary"
              className="w-[80%]"
              onClick={handleSearch}
              loading={isSearching}
            >
              搜索
            </Button>
            <Button
              onClick={handleReset}
              htmlType="submit"
              type="secondary"
              className="w-[80%]"
            >
              清空参数
            </Button>
            <FileUploader onUploadSuccess={() => {}} onUploadError={() => {}} />
            { }
          </div>
        </div>
      </Form>

      { }
      <div className="pr-28 mt-4 overflow-y-auto">
        <Table
          rowKey="onlyKey"
          columns={columnsSet[DATA_SOURCE_TABLE[activeTab]]}
          scroll={{ x: true, y: "58vh" }}
          data={dataSource?.list ?? []}
          renderPagination={() => (
            <div className="flex justify-end mt-2">
              <Pagination
                total={dataSource?.total ?? 0}
                onChange={handlePageChange}
                pageSize={pageSize}
              />
            </div>
          )}
          rowSelection={{
            type: "checkbox",
            onChange: (keys: Array<string>) => {
              
              console.log("onChange:", keys);
              setSelectedRowKeys(keys);
            },
            onSelect: (selected, record, selectedRows) => {
              console.log("onSelect:", selected, record, selectedRows);
            },
          }}
        />
      </div>
    </div>
  );
};

export default SearchTable;
