import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

export default defineConfig({
  base: "/petroleum-data-storage/",
  plugins: [react()],
  server: {
    proxy: {
      "/api": {
        target: "http://47.108.223.152:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});
