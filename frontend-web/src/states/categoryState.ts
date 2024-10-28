import { atom, selectorFamily } from 'recoil';
import { PATH } from '@/constants/path';
import { Category } from '@/types';

// const categoryStateDefault = {
//   All: true,
//   Meal: false,
//   Diaper: false,
//   Sleep: false,
//   Pump: false,
//   Activity: false,
//   Health: false,
//   Extra: false,
// };

// Recoil atom을 사용하여 CategoryState를 선언
// <CategoryStateType> 이라고 쓰는 것은 atom이라는 함수에 CategoryStateType 타입의 인자를 주겠다는 것을 의미
// = 타입 정보를 동적으로 설정할 수 있게 해주는 제너릭 문법
export const recordCategoryState = atom<Category>({
  key: 'recordCategoryState',
  default: 'All',
});

export const graphCategoryState = atom<Category>({
  key: 'graphCategoryState',
  default: 'Meal',
});

//
export const selectedCategoryState = selectorFamily<Category, string>({
  key: 'selectedCategoryState',
  get:
    (path: string) =>
    ({ get }) => {
      let state;
      switch (path) {
        case PATH.MAIN:
          state = recordCategoryState;
          break;
        case PATH.GRAPH:
          state = graphCategoryState;
          break;
      }
      if (state) {
        return get(state);
      } else {
        return 'All';
      }
    },
  set:
    (path: string) =>
    ({ set }, value) => {
      let state;
      switch (path) {
        case PATH.MAIN:
          state = recordCategoryState;
          break;
        case PATH.GRAPH:
          state = graphCategoryState;
          break;
      }
      if (state) {
        set(state, value);
      }
    },
});
