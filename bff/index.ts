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

router.post(
  "/bff/:path*",
  async (
    ctx: Koa.ParameterizedContext
    // next: () => Promise<any>
  ) => {
    const { path } = ctx.params;
    const isBffCute = isTrue(ctx.request.headers["is-bff-cute"]);
    const url = isBffCute ? `http://localhost:8080/petroleum/${path}` : "";
    console.log("url?\n", url, ctx.request.headers);
    try {
      const response = await axios({
        method: ctx.request.method,
        url,
        headers: ctx.request.headers,
        data: ctx.request.body,
        params: ctx.request.query,
      });

      ctx.status = response.status;
      ctx.body = response.data;
    } catch (error) {
      ctx.throw(error.response ? error.response.status : 500);
    }
  }
);

app.use(router.routes());
app.use(router.allowedMethods());

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
