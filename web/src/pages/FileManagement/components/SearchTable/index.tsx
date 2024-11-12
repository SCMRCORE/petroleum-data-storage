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
  // Tabs,
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
// const TabPane = Tabs.TabPane;
const pageSize = 10;
// const displayModeList = [TableMode.CASE1];

type DataSourceType = Record<
  string,
  { list: Array<Partial<MixedItem>>; total: number }
>;

const SearchTable = () => {
  /** 搜索表单 ref */
  const [form] = Form.useForm();
  /** 编辑表单 ref */
  const [editForm] = Form.useForm();
  /** 弹窗是否可见 */
  const [isModalVisible, setIsModalVisible] = useState(false);
  /** 弹窗展示的每个文件内容 */
  const [uploadFileInfoList, setUploadFileInfoList] = useState([]);
  /** 弹窗展示的每个文件的名称 */
  const [uploadFileNameList, setUploadFileNameList] = useState([]);
  /** 列表数据 */
  const [dataSource, setDataSource] = useState<{
    total: number;
    list: Array<any>;
  }>({
    total: 0,
    list: [],
  });
  /** 分页 */
  const [pageIndex, setPageIndex] = useState<number>(1);
  /** 搜索加载态 */
  const [isSearching, setIsSearching] = useState(false);
  /** 删除加载态 */
  const [isDeleting, setIsDeleting] = useState(false);
  /** 是否打开编辑窗口 */
  const [isEditing, setIsEditing] = useState(false);
  /** 编辑窗口表单的数据 */
  const [editingData, setEditingData] = useState<Partial<MixedItem>>();
  const [activeFileUrl, setActiveFileUrl] = useState<string>("");
  /** 搜索参数 */
  // const [searchParams, setSearchParams] = useState<
  //   Record<string, string | number>
  // >({});

  /** 批量选择  */
  const [selectedRowKeys, setSelectedRowKeys] = useState<Array<string>>([]);
  /** 展示模式 */
  const [activeTab, setActiveTab] = useState<TableMode>(TableMode.CASE1);

  /** 搜索 */
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
      // 不知为何，finally 中取消 loading 的操作并没有生效
      setSelectedRowKeys([]);
      setIsSearching(false);
      setDataSource(res);
    } catch (err) {
      console.log("数据获取异常:", err);
    } finally {
      setIsSearching(false);
    }
  };

  /** 清空搜素参数 */
  const handleReset = () => {
    // setSearchParams({});
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
    // handleSearch, handleEdit
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
      {/* 搜索项 */}
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

      {/* 表格主体部分 */}
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
              // const onlyKeys = selectedRows.map((item) => item.onlyKey);
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
