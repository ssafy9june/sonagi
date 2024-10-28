import { atom } from 'recoil';

export const fetchCounterState = atom({
  key: 'fetchCounterState',
  default: 0,
});
