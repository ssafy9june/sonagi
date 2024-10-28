import { atom } from 'recoil';

// 선택된 백신접종 아이디 저장
export const selectedVaccinationState = atom<number>({
  key: 'selectedVaccinationState',
  default: 1,
});

// 선택된 건강검진 아이디 저장
export const selectedMedicalCheckState = atom<number>({
  key: 'selectedMedicalCheckState',
  default: 1,
});
