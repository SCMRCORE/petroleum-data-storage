import {
  Form,
  Grid,
  Input,
  Button,
  Table,
  Space,
  Pagination,
} from "@arco-design/web-react";
import { search } from "../../../../services/searchTable.ts";
import { useEffect, useState } from "react";
import { columns, formConfigList } from "./configs.tsx";
import { MixedItem } from "../../../../types/index.ts";
import Item from "@arco-design/web-react/es/Breadcrumb/item.js";

// const defaultData = [...new Array(5)].map((_, index) => {
//   // return {
//   //   key: index,
//   //   name: "Jane Doe " + index,
//   //   salary: 23000,
//   //   gender: index % 2 > 0 ? "male" : "female",
//   //   age: 20 + index,
//   // };
// });
const pageSize = 20;
const SearchTable = () => {
  const [dataSource, setDataSource] = useState<Array<Partial<MixedItem>>>([]);
  const [searchParams, setSearchParams] = useState<
    Record<string, string | number>
  >({});
  const [pageIndex, setPageIndex] = useState<number>(1);
  const [total, setTotal] = useState(100);
  const handleSearch = async (params) => {
    try {
      const { list, total } = await search({ ...params });
      setDataSource([...list]);
      setTotal(total);
    } catch (err) {
      console.log("数据获取异常:", err);
    }
  };

  const handlePageChange = (index) => {
    setPageIndex(index);
  };

  useEffect(() => {
    handleSearch({ ...searchParams, pageIndex, pageSize });
  }, [pageIndex]);

  return (
    <div>
      {/* 搜索项 */}
      <Form.Provider
        onFormValuesChange={(_name, values) => {
          setSearchParams({ ...values });
        }}
        onFormSubmit={(_name, values, info) => {
          console.log(values, info);
          handleSearch({ ...values, pageIndex, pageSize });
        }}
      >
        <Form id="searchForm" layout="vertical">
          <div className="flex w-[100%] items-center">
            <div className="w-[84%] mr-[12px]">
              {formConfigList.map((rowItemList, index) => {
                return (
                  <Grid.Row gutter={24} key={index}>
                    {rowItemList.map((item) => {
                      return (
                        <Grid.Col span={6} key={item.field}>
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
            <div className="w-[8%] flex flex-col items-center gap-[12px] border-l-2">
              <Button htmlType="submit" type="primary" className="w-[80%]">
                搜索
              </Button>
              <Button
                htmlType="reset"
                type="secondary"
                // status=""
                className="w-[80%]"
              >
                重置
              </Button>
            </div>
          </div>
        </Form>
      </Form.Provider>

      {/* 表格主体部分 */}
      <div className="pr-28">
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
