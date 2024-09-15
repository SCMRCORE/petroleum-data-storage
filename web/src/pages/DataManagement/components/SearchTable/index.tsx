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
import { add, search } from "../../../../services/searchTable.ts";
import { useEffect, useState } from "react";
import {
  getColumns,
  formConfigList,
} from "./configs.tsx";
import { MixedItem } from "../../../../types/index.ts";
import * as XLSX from "xlsx";
import {
  checkDataSourceTable,
  DATA_SOURCE_TABLE,
  DATA_SOURCE_TABLE_TITLE_MAP,
  formatCnToEn,
} from "../../../../utils/checkDataSource.ts";

const pageSize = 20;
const SearchTable = () => {
  /** 表单 ref */
  const [form] = Form.useForm();
  /** 弹窗是否可见 */
  const [isModalVisible, setIsModalVisible] = useState(false);
  /** 弹窗展示的每个文件内容 */
  const [uploadFileInfoList, setUploadFileInfoList] = useState([]);
  /** 弹窗展示的每个文件的名称 */
  const [uploadFileNameList, setUploadFileNameList] = useState([]);
  /** 列表数据 */
  const [dataSource, setDataSource] = useState<Array<Partial<MixedItem>>>([]);
  /** 分页 */
  const [pageIndex, setPageIndex] = useState<number>(1);
  /** 分页总数 */
  const [total, setTotal] = useState(100);
  /** 搜索参数 */
  const [searchParams, setSearchParams] = useState<
    Record<string, string | number>
  >({});

  /** 搜索 */
  const handleSearch = async () => {
    try {
      const { list, total } = await search({
        ...searchParams,
        pageIndex,
        pageSize,
      });
      setDataSource([...list]);
      setTotal(total);
    } catch (err) {
      console.log("数据获取异常:", err);
    }
  };

  /** 清空搜素参数 */
  const handleReset = () => {
    setSearchParams({});
    form.resetFields();
  };

  /** 读取Excel，打开弹窗 */
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
            const enFileRowList = fileRowList.map((fileRow) => {
              const newRow = {};
              Object.keys(fileRow).forEach((key) => {
                const positiveEn = formatCnToEn(key)
                newRow[positiveEn] = fileRow[key];
              });
              return newRow;
            });

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
    input.click(); // 触发文件选择框
  };

  const confirmUpload = async () => {
    console.log("rowList", uploadFileInfoList);
    try {
      const res = await add(uploadFileInfoList);
      console.log("rowList res", res);
    } catch (err) {
      Message.error(`上传失败: ${err}`);
    } finally {
      setUploadFileInfoList([])
      setUploadFileNameList([])
    }
  };

  const handlePageChange = (index) => {
    setPageIndex(index);
  };

  useEffect(() => {
    handleSearch();
  }, [pageIndex]);


  const columns = getColumns(handleSearch)

  return (
    <div>
      {/* 弹窗 */}
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
            // TODO: 仔细一想，其实这里不能取 sample 来代表所有表的类型，每个 listItem 都需要单独判断。。。不过先这么用吧。。。
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

      {/* 搜索项 */}
      <Form.Provider
        onFormValuesChange={(_name, values) => {
          setSearchParams({ ...values });
        }}
        onFormSubmit={(_name, values, info) => {
          console.log(values, info);
          handleSearch();
        }}
      >
        <Form form={form} id="searchForm" layout="vertical">
          <div className="flex w-[100%] items-center">
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
              <Button htmlType="submit" type="primary" className="w-[80%]">
                搜索
              </Button>
              <Button
                onClick={handleReset}
                htmlType="submit"
                type="secondary"
                className="w-[80%]"
              >
                重置搜索
              </Button>
              <Button
                onClick={openUploadFileModal}
                type="secondary"
                className="w-[80%]"
              >
                EXCEL导入
              </Button>
            </div>
          </div>
        </Form>
      </Form.Provider>

      {/* 表格主体部分 */}
      <div className="pr-28 mt-4">
        <Table
          columns={[...columns]}
          scroll={{ x: true, y: 500 }}
          data={dataSource}
          renderPagination={() => (
            <div className="flex justify-end mt-2">
              <Pagination
                total={total}
                onChange={handlePageChange}
                pageSize={pageSize}
              />
            </div>
          )}
        />
      </div>
    </div>
  );
};

export default SearchTable;
