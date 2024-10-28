import { atom } from 'recoil';
import { BabiesOfUser, Baby } from '@/types';
import { recoilPersist } from 'recoil-persist';

// localStorage에 저장할 필요가 있겠...쬬?
const { persistAtom } = recoilPersist({
  key: 'BabyState',
  storage: localStorage,
});

// 아기 정보
export const babyInfoState = atom<Baby>({
  key: 'babyInfoState',
  default: {} as Baby,
  effects_UNSTABLE: [persistAtom],
});

// 유저의 아이들 정보
export const babiesOfUserState = atom<BabiesOfUser[]>({
  key: 'babiesOfUserState',
  default: [] as BabiesOfUser[],
  effects_UNSTABLE: [persistAtom],
});

// 현재 선택된 아기 정보
export const selectedBabyState = atom<BabiesOfUser>({
  key: 'selectedBabyState',
  default: {} as BabiesOfUser,
  effects_UNSTABLE: [persistAtom],
});
