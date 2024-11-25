import Koa from "koa";
import Router from "koa-router";
import bodyParser from "koa-bodyparser";
import axios from "axios";
import { isTrue } from "./utils/str";

const port = 2233;
const app = new Koa();
const router = new Router();
 
app.use(bodyParser());
 
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

const service = async (
  ctx: Koa.ParameterizedContext
  
) => {
  const { path } = ctx.params;
  const isBffCute = isTrue(ctx.request.headers["is-bff-cute"]);
  const url = isBffCute ? `http://localhost:8080/${path}` : "";
  
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
    
    console.log(`==== response error ====\n`);
    console.log(error);
    console.log(`~~~~ response error ~~~~`);
    ctx.throw(error.response ? error.response.status : 500);
  }
};
router.post("/bff/:path*", service);
router.get("/bff/:path*", service);
router.put("/bff/:path*", service);

app.use(router.routes());
app.use(router.allowedMethods());

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
