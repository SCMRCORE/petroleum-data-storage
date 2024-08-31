import { create } from 'zustand'

interface StoreState {
  count: number;
}

interface StoreActions {
  inc: () => void;
}

const useStore = create<StoreState & StoreActions>((set) => ({
  count: 1,
  inc: () => set((state) => ({ count: state.count + 1 })),
}));


export default useStore