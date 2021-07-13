import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import { resolve } from 'path';

function pathResolve(dir: string) {
  return resolve(__dirname, '.', dir);
}
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  alias: {
    '/@/': pathResolve('src'),
  },
  server: {
    port: 8090,
    proxy: { '/api': { target: 'http://localhost:8080/', changeOrigin: true } },
  },
});
