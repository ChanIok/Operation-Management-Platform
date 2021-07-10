import { createStore } from 'vuex';

// Create a new store instance.
const store = createStore({
  state() {
    return {
      event: '',
      count: 0,
      asideIsCollapse: false,
      width: 1920,
    };
  },
  mutations: {
    increment(state: any) {
      state.count++;
    },
    setEvent(state, eventName: string) {
      state.event = eventName;
    },
    setAsideIsCollapse(state, eventName) {
      state.asideIsCollapse = eventName;
    },
    setWidth(state, eventName) {
      state.width = eventName;
    },
  },
});

export default store;
