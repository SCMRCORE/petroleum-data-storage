import { create } from 'zustand'

// TODO: 草，zustan 会在 swc + react 19 + ts 下报错，先留着这个 demo，后面给官方提 issue
const useStore = create((set) => ({
  count: 1,
  inc: () => set((state: any) => ({ count: state.count + 1 })),
}))


export default useStore