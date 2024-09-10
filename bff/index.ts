import Koa from "koa";
import Router from "koa-router";
import bodyParser from "koa-bodyparser";
import axios from "axios";
import { isTrue } from "./utils/str";

const port = 2233;
const app = new Koa();
const router = new Router();

// 使用中间件解析请求体
app.use(bodyParser());

// 设置CORS
app.use(async (ctx: Koa.Context, next: () => Promise<any>) => {
  ctx.set("Access-Control-Allow-Origin", "*");
  ctx.set(
    "Access-Control-Allow-Headers",
    "Content-Type, Authorization, X-Requested-With, Is-BFF-Cute"
  );
  ctx.set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
  if (ctx.request.method === "OPTIONS") ctx.body = "";
  else await next();
});

// TODO: 这里可以抽离出 controller 和 service
const service = async (
  ctx: Koa.ParameterizedContext
  // next: () => Promise<any>
) => {
  const { path } = ctx.params;
  const isBffCute = isTrue(ctx.request.headers["is-bff-cute"]);
  const url = isBffCute ? `http://localhost:8080/${path}` : "";
  // console.log("url?\n", url, ctx.request);
  try {
    const response = await axios({
      method: ctx.request.method,
      url,
      headers: ctx.request.headers,
      data: ctx.request.body,
      params: ctx.request.query,
    });
    // console.log("response", url, response);
    ctx.status = response.status;
    ctx.body = response.data;
  } catch (error) {
    // const { url, method, data, params } = error;
    console.log(`==== response error ====\n`);
    console.log(error);
    console.log(`~~~~ response error ~~~~`);
    ctx.throw(error.response ? error.response.status : 500);
  }
};
router.post("/bff/:path*", service);
router.get("/bff/:path*", service);

app.use(router.routes());
app.use(router.allowedMethods());

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
