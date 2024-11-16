import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.js";
import "@arco-design/web-react/dist/css/arco.css";
import "tailwindcss/tailwind.css";
import "./index.css";

createRoot(document.getElementById("root")!).render(
  // <StrictMode>
  <App />
  // </StrictMode>
);
