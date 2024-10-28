import { User } from '@/types';
import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

// localStorage 에 저장
const { persistAtom } = recoilPersist({
  key: 'UserState',
  storage: localStorage,
});

// 사용자 정보
export const userInfoState = atom<User>({
  key: 'userInfoState',
  default: {} as User,
  effects_UNSTABLE: [persistAtom],
});

// 로그인 여부
export const logInState = atom<boolean>({
  key: 'logInState',
  default: false,
  effects_UNSTABLE: [persistAtom],
});

export const userFcmTokenState = atom<string>({
  key: 'userFcmTokenState',
  default: '',
  effects_UNSTABLE: [persistAtom],
});
