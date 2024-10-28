import { instance } from '@/apis/instance';
import { BabyMemo, EditBabyMemo } from '@/types';

// 질병 메모 추가
const addIllness = async (memo: BabyMemo) => {
  try {
    await instance.post('/illness', memo);
    console.log('memoAPI.ts success');
  } catch {
    new Error('Illness Memo Register Error');
    console.log('memoAPI.ts error');
  }
};

// 질병 메모 삭제
const deleteIllness = async (illnessId: number) => {
  try {
    await instance.delete(`illness/${illnessId}`);
  } catch {
    new Error('Illness Memo delete Error');
  }
};

// 질병 메모 조회
const getIllness = async (babyId: number) => {
  try {
    const response = await instance.get(`/illness/${babyId}`);
    return response.data;
  } catch {
    new Error('no data returned from the API');
  }
};

// 질병 메모 수정
const editIllness = async (memo: EditBabyMemo) => {
  try {
    await instance.put('/illness', memo);
    console.log('memoAPI.ts success');
  } catch {
    new Error('Illness Memo Edit Error');
    console.log('memoAPI.ts error');
  }
};

// 주의사항

const addCaution = async (memo: BabyMemo) => {
  try {
    await instance.post('/caution', memo);
    console.log('memoAPI.ts success');
  } catch {
    new Error('caution Memo Register Error');
    console.log('memoAPI.ts error');
  }
};

// 질병 메모 삭제
const deleteCaution = async (cautionId: number) => {
  try {
    await instance.delete(`caution/${cautionId}`);
  } catch {
    new Error('caution Memo delete Error');
  }
};

// 질병 메모 조회
const getCaution = async (babyId: number) => {
  try {
    const response = await instance.get(`/caution/${babyId}`);
    return response.data;
  } catch {
    new Error('no data returned from the API');
  }
};

// 질병 메모 수정
const editCaution = async (memo: EditBabyMemo) => {
  try {
    await instance.put('/caution', memo);
    console.log('memoAPI.ts success');
  } catch {
    new Error('caution Memo Edit Error');
    console.log('memoAPI.ts error');
  }
};

export {
  addIllness,
  deleteIllness,
  getIllness,
  editIllness,
  addCaution,
  deleteCaution,
  getCaution,
  editCaution,
};
