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
  Tabs,
} from "@arco-design/web-react";
import {
  add,
  deleteItem,
  search,
  updateItem,
} from "../../../../services/searchTable.ts";
import { useEffect, useMemo, useState } from "react";
import { getColumns, formConfigList, EN_2_CN_TABLES } from "./configs.tsx";
import { MixedItem } from "../../../../types/index.ts";
import * as XLSX from "xlsx";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  DATA_SOURCE_TABLE_TITLE_MAP,
  formatCnToEn,
} from "../../../../utils/checkDataSource.ts";
const TabPane = Tabs.TabPane;
const pageSize = 10;
const displayModeList = Object.keys(DATA_SOURCE_TABLE).filter(
  (k) => typeof DATA_SOURCE_TABLE[k] === "number"
);

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
   
  const [dataSource, setDataSource] = useState<DataSourceType>();
   
  const [pageIndex, setPageIndex] = useState<number>(1);
   
  const [isSearching, setIsSearching] = useState(false);
   
  const [isDeleting, setIsDeleting] = useState(false);
   
  const [isEditing, setIsEditing] = useState(false);
   
  const [editingData, setEditingData] = useState<Partial<MixedItem>>();
   
  
  
  

   
  const [selectedRowKeys, setSelectedRowKeys] = useState<Array<string>>([]);
   
  const [activeTab, setActiveTab] = useState<DATA_SOURCE_TABLE>(
    DATA_SOURCE_TABLE.JS
  );

   
  const handleSearch = async () => {
    try {
      setIsSearching(true);
      const formData = form.getFieldsValue();
      const res = await search({
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

   
  const openUploadFileModal = () => {
    const input = document.createElement("input");
    input.type = "file";
    input.accept = ".xls,.xlsx";
    input.onchange = (e) => {
      const target = e.target as unknown as { files: Array<File> };
      const file = target?.files?.[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          const bstr = e.target.result;
          const wb = XLSX.read(bstr, { type: "binary" });
          const newUploadFileInfoList = [];
          const newUploadFileNameList = [];
          wb.SheetNames?.forEach((wsName) => {
            const ws = wb.Sheets[wsName];
            const fileRowList = XLSX.utils.sheet_to_json(ws);
            console.log("原始上传的表", fileRowList);

            const enFileRowList = fileRowList.map((fileRow) => {
              const newRow = {};
              Object.keys(fileRow).forEach((key) => {
                const positiveEn = formatCnToEn(key);
                newRow[positiveEn] = fileRow[key];
              });
              return newRow;
            });
            console.log("翻译后的表", enFileRowList);

            newUploadFileInfoList.push(enFileRowList);
            newUploadFileNameList.push(wsName);
          });
          setIsModalVisible(true);
          setUploadFileInfoList([...newUploadFileInfoList]);
          setUploadFileNameList([...newUploadFileNameList]);
        };
        reader.readAsBinaryString(file);
        input.remove();
      }
    };
    input.click(); 
  };

  const confirmUpload = async () => {
    console.log("rowList", uploadFileInfoList);
    try {
      const res = await add(uploadFileInfoList);
      setIsModalVisible(false);
      handleSearch();
      console.log("rowList res", res);
    } catch (err) {
      Message.error(`上传失败: ${err}`);
    } finally {
      setUploadFileInfoList([]);
      setUploadFileNameList([]);
    }
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

  const handleTabChange = (v) => {
    
    setActiveTab(v);
  };

  const handleEditFormClose = () => {
    setIsEditing(false);
    editForm.resetFields();
    setEditingData({});
  };
  const handleEdit = (rowData) => {
    setIsEditing(true);
    setEditingData(rowData);
    editForm.setFieldsValue(rowData);
  };

  const confirmEdit = async () => {
    try {
      const formValue = editForm.getFieldsValue();
      console.log("formValue", formValue);
      await updateItem({
        num: activeTab,
        onlyKey: editingData.onlyKey,
        rowData: formValue,
      });
      handleSearch();
    } catch (err) {
      console.error("修改失败：", err);
    } finally {
      setIsEditing(false);
    }
  };

  useEffect(() => {
    handleSearch();
  }, [pageIndex]);

  const columnsSet = useMemo(() => getColumns(handleSearch, handleEdit), []);

  return (
    <div>
      <Modal
        title="确认是否要上传文件"
        visible={isModalVisible}
        onOk={confirmUpload}
        onCancel={() => setIsModalVisible(false)}
        autoFocus={false}
        focusLock={true}
      >
        <div className="overflow-y-auto max-h-[400px]">
          {uploadFileInfoList?.map((fileRowList, index) => {
            
            const sampleFileRow = fileRowList[0];
            const keys = Object.keys(sampleFileRow);
            const dataSourceTable = checkDataSourceTable(keys);
            const titleKey = DATA_SOURCE_TABLE[dataSourceTable];
            const tableTitle = DATA_SOURCE_TABLE_TITLE_MAP[titleKey];
            const len = fileRowList?.length ?? 0;

            return (
              <div key={index} className="flex justify-between gap-2 mt-2">
                <div className="text-clip flex-1">
                  文件名称：{uploadFileNameList[index]}
                </div>
                <div>
                  <Tag color={tableTitle ? "arcoblue" : "red"}>
                    {tableTitle || "【表头字段不匹配】"}({len}条数据)
                  </Tag>
                </div>
              </div>
            );
          })}
        </div>
      </Modal>

      <Modal
        visible={isEditing}
        onCancel={handleEditFormClose}
        footer={
          <div className="flex gap-2">
            <Button type="primary" onClick={confirmEdit}>
              确认修改
            </Button>
            <Button onClick={handleEditFormClose}>取消</Button>
          </div>
        }
        className="w-[800px]"
      >
        <Form
          id="editForm"
          layout="inline"
          className="w-[700px] min-h-[300px]"
          form={editForm}
        >
          {Object.keys(editingData ?? {})?.map((enKey) => {
            const value = editingData[enKey];
            const tableName = DATA_SOURCE_TABLE[activeTab];
            const cnKey = EN_2_CN_TABLES[tableName][enKey];
            
            if (["status", "onlyKey", "num"].includes(enKey)) {
              return null;
            }

            return (
              <Form.Item
                initialValue={value}
                label={cnKey || enKey}
                field={enKey}
                key={enKey}
              >
                <Input />
              </Form.Item>
            );
          })}
        </Form>
      </Modal>
      <Tabs
        defaultActiveTab={`${activeTab}`}
        onChange={handleTabChange}
        activeTab={`${activeTab}`}
      >
        {displayModeList.map((item, index) => {
          if (item === DATA_SOURCE_TABLE[DATA_SOURCE_TABLE.ALL]) return null;
          return (
            <TabPane
              key={`${index}`}
              title={`${DATA_SOURCE_TABLE_TITLE_MAP[item]}`}
            >
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
                    <Button
                      onClick={openUploadFileModal}
                      type="secondary"
                      className="w-[80%]"
                    >
                      EXCEL导入
                    </Button>
                    {selectedRowKeys?.length > 0 && (
                      <Button
                        onClick={batchDelete}
                        type="primary"
                        className="w-[80%]"
                        status="danger"
                        loading={isDeleting}
                      >
                        批量删除
                      </Button>
                    )}
                  </div>
                </div>
              </Form>

              { }
              <div className="pr-28 mt-4 overflow-y-auto">
                <Table
                  rowKey="onlyKey"
                  columns={columnsSet[DATA_SOURCE_TABLE[activeTab]]}
                  scroll={{ x: true, y: "58vh" }}
                  data={dataSource?.[DATA_SOURCE_TABLE[activeTab]]?.list ?? []}
                  renderPagination={() => (
                    <div className="flex justify-end mt-2">
                      <Pagination
                        total={
                          dataSource?.[DATA_SOURCE_TABLE[activeTab]]?.total ?? 0
                        }
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
            </TabPane>
          );
        })}
      </Tabs>
    </div>
  );
};

export default SearchTable;
