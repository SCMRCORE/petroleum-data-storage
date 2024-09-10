import {
  Form,
  Grid,
  Input,
  Button,
  Message,
  Table,
  Space,
} from "@arco-design/web-react";
import { search } from "../../../../services/searchTable.ts";
import { useEffect } from "react";
import { formConfigList } from "./configs.tsx";

const defaultData = [...new Array(5)].map((_, index) => {
  return {
    key: index,
    name: "Jane Doe " + index,
    salary: 23000,
    gender: index % 2 > 0 ? "male" : "female",
    age: 20 + index,
  };
});
// function RefreshForm() {
//   return (
//     <Form id="refreshForm" layout="inline" style={{ width: "auto" }}>
//       <Form.Item field="keyword">
//         <Input.Search placeholder="enter keyword" />
//       </Form.Item>
//       <Button htmlType="submit">Refresh</Button>
//     </Form>
//   );
// }

const SearchTable = () => {
  const handleSearch = async (params) => {
    try {
      const res = await search({ ...params });
      console.log(res);
    } catch (err) {
      console.log("数据获取异常:", err);
      // alert("数据获取异常，详情请参考控制台");
    }
  };

  useEffect(() => {
    search({});
  }, []);

  return (
    <div>
      {/* 搜索项 */}
      <Form.Provider
        onFormValuesChange={(name, changedValues, info) => {
          // console.log("onFormValuesChange: ", name, changedValues, info);
        }}
        onFormSubmit={(name, values, info) => {
          // console.log("onFormSubmit: ", name, values, info);
          handleSearch({ ...values });
        }}
      >
        <Form id="searchForm" layout="vertical">
          {formConfigList.map((rowItemList, index) => {
            return (
              <Grid.Row gutter={24} key={index}>
                {rowItemList.map((item) => {
                  return (
                    <Grid.Col span={8} key={item.field}>
                      <Form.Item
                        label={item.label}
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

          <Space>
            <Button htmlType="submit" type="primary">
              搜索
            </Button>
          </Space>
        </Form>
      </Form.Provider>

      {/* 表格主体部分 */}
      <Table
        columns={[
          {
            title: "Name",
            dataIndex: "name",
          },
          {
            title: "Salary",
            dataIndex: "salary",
          },
          {
            title: "Gender",
            dataIndex: "gender",
          },
          {
            title: "Age",
            dataIndex: "age",
          },
          {
            title: "Email",
            dataIndex: "email",
          },
        ]}
        data={defaultData}
      />
    </div>
  );
};

export default SearchTable;
