import {
  Form,
  Grid,
  Input,
  Button,
  Message,
  Tag,
  Table,
  InputNumber,
  Space,
} from "@arco-design/web-react";
import { search } from "../../../../services/searchTable.ts";
import { useEffect } from "react";

const defaultData = [...new Array(5)].map((_, index) => {
  return {
    key: index,
    name: "Jane Doe " + index,
    salary: 23000,
    email: "jane.doe@example.com",
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
  const handleSubmit = (e) => {
    // e.preventDefault();
    // const form = document.getElementById("searchForm");
    // form.submit();
  };

  useEffect(() => {
    search({});
  }, []);

  return (
    <div>
      {/* 搜索项 */}
      <Form.Provider
        onFormValuesChange={(name, changedValues, info) => {
          console.log("onFormValuesChange: ", name, changedValues, info);
        }}
        onFormSubmit={(name, values, info) => {
          console.log("onFormSubmit: ", name, values, info);
          if (name === "modalForm") {
            info.forms.searchForm.setFieldsValue({
              email: values.email,
            });
          }
          Message.info({
            icon: <span></span>,
            content: (
              <div style={{ textAlign: "left" }}>
                <span>form values:</span>
                <pre>
                  {JSON.stringify(
                    {
                      ...info.forms.searchForm.getFieldsValue(),
                      ...info.forms.refreshForm.getFieldsValue(),
                    },
                    null,
                    2
                  )}
                </pre>
              </div>
            ),
          });
        }}
      >
        <Form id="searchForm" layout="vertical">
          <Grid.Row gutter={24}>
            <Grid.Col span={8}>
              <Form.Item label="Name" field="name">
                <Input placeholder="enter name" />
              </Form.Item>
            </Grid.Col>

            <Grid.Col span={8}>
              <Form.Item label="Age" field="age">
                <InputNumber placeholder="enter age" />
              </Form.Item>
            </Grid.Col>
          </Grid.Row>
          <Space>
            <Form.Item field="email" shouldUpdate noStyle>
              {(values) => {
                return (
                  <Tag color="arcoblue">email: {values.email || "null"}</Tag>
                );
              }}
            </Form.Item>
            <Button onClick={handleSubmit} htmlType="submit" type="primary">
              Search
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
